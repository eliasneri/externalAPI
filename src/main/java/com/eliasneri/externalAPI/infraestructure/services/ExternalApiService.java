package com.eliasneri.externalAPI.infraestructure.services;

import com.eliasneri.externalAPI.apiv1.dto.ProductsDTO;
import com.eliasneri.externalAPI.infraestructure.converter.ProductConverter;
import com.eliasneri.externalAPI.infraestructure.clients.ExternalApiClient;
import com.eliasneri.externalAPI.infraestructure.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final ProductConverter converter;
    private final ProductService service;

    public List<ProductsDTO> getAllProducts() {
        List<ProductsDTO> dto = converter.toListDTO(service.getAll());
        return dto;
    }

}
