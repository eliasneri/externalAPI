package com.eliasneri.externalAPI.apiv1.dto;

import com.eliasneri.externalAPI.business.services.FakeApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "ExternalAPI")
public class FakeApiController {

    private final FakeApiService service;

    @Operation(summary = "Busca todos os Produtos", method = "GET")
    @GetMapping("/findall")
    public ResponseEntity<List<ProductsDTO>> getProducts() {
        return ResponseEntity
                .ok(service.getAllProducts());
    }

    @Operation(summary = "Busca Produto(s) por Título", method = "GET")
    @GetMapping("/title/find/{title}")
    public ResponseEntity<List<ProductsDTO>> getByLikeName(@Valid @PathVariable String title) {
        return ResponseEntity.ok(service.getByTitleLike(title));

    }

}
