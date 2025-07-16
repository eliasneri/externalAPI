package com.eliasneri.externalAPI.business.services;

import com.eliasneri.externalAPI.apiv1.dto.ProductsDTO;
import com.eliasneri.externalAPI.business.converter.ProductConverter;
import com.eliasneri.externalAPI.infraestructure.client.FakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final FakeApiClient client;
    private final ProductConverter converter;
    private final ProductService service;

    public List<ProductsDTO> getAllProducts() {
        List<ProductsDTO> dto = client.getAllProducts();
        dto.forEach(prod -> {
                boolean existId = service.existById(prod.getId());
                if (!existId) {
                    service.saveProduct(converter.toEntity(prod));
                }
            }
        );

        return converter.toListDTO(service.getAll());

    }
}
