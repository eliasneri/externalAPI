package com.eliasneri.externalAPI.infraestructure.jobs;

import com.eliasneri.externalAPI.apiv1.dto.ProductsDTO;
import com.eliasneri.externalAPI.infraestructure.Metrics.ProductSyncMetrics;
import com.eliasneri.externalAPI.infraestructure.clients.ExternalApiClient;
import com.eliasneri.externalAPI.infraestructure.services.AsyncProductService;
import io.micrometer.core.instrument.MeterRegistry;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class ProductSyncJob {

    private final ExternalApiClient client;
    private final AsyncProductService asyncService;
    private final MeterRegistry meterRegistry;
    private final ProductSyncMetrics metrics;

    public ProductSyncJob(ExternalApiClient client,
                          AsyncProductService asyncService,
                          MeterRegistry meterRegistry,
                          ProductSyncMetrics metrics) {
        this.client = client;
        this.asyncService = asyncService;
        this.meterRegistry = meterRegistry;
        this.metrics = metrics;
    }

    @Scheduled(cron = "0 */10 * * * *") // A cada 10 minutos
    @SchedulerLock(name = "productSyncJob",
            lockAtLeastFor = "5m",
            lockAtMostFor = "9m")
    public void syncProducts() {
        metrics.incrementExecutionCount();
        long startTime = System.currentTimeMillis();

        try {
            List<ProductsDTO> products = client.getAllProducts();
            metrics.recordApiCallSuccess();
            metrics.setLastFetchedCount(products.size());

            asyncService.saveProductsAsync(products)
                    .thenRun(() -> {
                        long duration = System.currentTimeMillis() - startTime;
                        metrics.recordSuccessfulSync(duration);
                        meterRegistry.timer("product.sync.job.duration")
                                .record(duration, TimeUnit.MILLISECONDS);
                        System.out.println("sync realized");
                    })
                    .exceptionally(ex -> {
                        metrics.recordSyncFailure();
                        meterRegistry.counter("product.sync.job.failures").increment();
                        return null;
                    });

        } catch (Exception e) {
            metrics.recordApiCallFailure();
            meterRegistry.counter("product.sync.job.api.failures").increment();
        }
    }


}
