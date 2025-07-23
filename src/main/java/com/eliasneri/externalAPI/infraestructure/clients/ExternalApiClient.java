package com.eliasneri.externalAPI.infraestructure.clients;

import com.eliasneri.externalAPI.domain.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "external-api", url = "${external-api.url:#{null}}")
public interface ExternalApiClient {

    @GetMapping("/products")
    List<ProductsDTO> getAllProducts();

}
