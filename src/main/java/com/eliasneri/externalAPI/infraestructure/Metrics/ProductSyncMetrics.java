package com.eliasneri.externalAPI.infraestructure.Metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductSyncMetrics {

    private final MeterRegistry meterRegistry;
    private AtomicInteger lastFetchedCount = new AtomicInteger(0);
    private AtomicLong executionCount = new AtomicLong(0);
    private AtomicLong successCount = new AtomicLong(0);
    private AtomicLong failureCount = new AtomicLong(0);

    public ProductSyncMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        Gauge.builder("product.sync.last.fetched.count", lastFetchedCount::get)
                .description("Número de produtos obtidos na última sincronização")
                .register(meterRegistry);

        Gauge.builder("product.sync.execution.count", executionCount::get)
                .description("Contagem total de execuções do job")
                .register(meterRegistry);

        Gauge.builder("product.sync.success.count", successCount::get)
                .description("Contagem de sincronizações bem-sucedidas")
                .register(meterRegistry);

        Gauge.builder("product.sync.failure.count", failureCount::get)
                .description("Contagem de falhas na sincronização")
                .register(meterRegistry);
    }

    public void incrementExecutionCount() {
        executionCount.incrementAndGet();
    }

    public void recordSuccessfulSync(long duration) {
        successCount.incrementAndGet();
    }

    public void recordSyncFailure() {
        failureCount.incrementAndGet();
    }

    public void recordApiCallSuccess() {
        meterRegistry.counter("product.api.call.success").increment();
    }

    public void recordApiCallFailure() {
        meterRegistry.counter("product.api.call.failure").increment();
    }

    public void setLastFetchedCount(int count) {
        lastFetchedCount.set(count);
    }

}
