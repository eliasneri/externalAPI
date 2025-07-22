package com.eliasneri.externalAPI.infraestructure.services;

import com.eliasneri.externalAPI.apiv1.dto.ProductsDTO;
import com.eliasneri.externalAPI.infraestructure.converter.ProductConverter;
import com.eliasneri.externalAPI.infraestructure.entities.ProductEntity;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncProductService {

    private final ProductService productService;
    private final ProductConverter converter;
    private final MeterRegistry meterRegistry;

    @Async
    public CompletableFuture<Void> saveProductsAsync(List<ProductsDTO> products) {
        try {
            List<ProductEntity> count = new ArrayList<>();
            products.forEach(prod -> {
                if (!productService.existById(prod.getId())) {
                    ProductEntity entity = converter.toEntity(prod);
                    count.add(productService.saveProduct(entity));
                }
            });
            meterRegistry.counter("products.saved.count").increment(products.size());
            return CompletableFuture.completedFuture(null);
        } catch (Exception e) {
            meterRegistry.counter("products.save.errors").increment();
            throw e;
        }
    }
}
