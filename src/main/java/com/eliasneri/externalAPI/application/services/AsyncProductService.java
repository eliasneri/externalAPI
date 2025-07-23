package com.eliasneri.externalAPI.application.services;

import com.eliasneri.externalAPI.domain.dto.ProductsDTO;
import com.eliasneri.externalAPI.application.converter.ProductConverter;
import com.eliasneri.externalAPI.domain.entities.ProductEntity;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
            products.forEach(prod -> {
                if (!productService.existById(prod.getId())) {
                    ProductEntity entity = converter.toEntity(prod);
                    productService.saveProduct(entity);
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
