package com.eliasneri.externalAPI.application.services;

import com.eliasneri.externalAPI.domain.dto.ProductsDTO;
import com.eliasneri.externalAPI.application.converter.ProductConverter;
import io.prometheus.metrics.core.metrics.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final ProductConverter converter;
    private final ProductService service;

    private final Counter findAllCounter;

    public List<ProductsDTO> getAllProducts() {
        List<ProductsDTO> dto = converter.toListDTO(service.getAll());
        return dto;
    }

}
