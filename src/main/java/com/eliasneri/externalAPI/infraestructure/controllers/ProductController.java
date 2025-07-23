package com.eliasneri.externalAPI.infraestructure.controllers;

import com.eliasneri.externalAPI.domain.dto.ProductsDTO;
import com.eliasneri.externalAPI.application.services.ExternalApiService;
import com.eliasneri.externalAPI.application.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "ExternalAPI")
public class ProductController {

    private final ExternalApiService externalService;
    private final ProductService productService;

    @Operation(summary = "Busca todos os Produtos na API Externa e Salva os registros no banco de Dados!", method = "GET")
    @GetMapping("/findall")
    public ResponseEntity<List<ProductsDTO>> getProducts() {
        return ResponseEntity.ok(externalService.getAllProducts());
    }

    @Operation(summary = "Busca Produto(s) por Título", method = "GET")
    @GetMapping("/title/find/{title}")
    public ResponseEntity<List<ProductsDTO>> getByLikeName(@Valid @PathVariable String title) {
        return ResponseEntity.ok(productService.getByLikeTitle(title));
    }

    @Operation(summary = "Deletar um produto por UUID", method = "DELETE")
    @DeleteMapping("/delete/{uuid}")
    @Transactional
    public ResponseEntity<String> delete (@Valid @PathVariable String uuid) {
        return ResponseEntity.ok(productService.deleteByUuid(uuid));
    }

    public ResponseEntity<ProductsDTO> findById (@Valid @PathVariable String uuid) {
        return ResponseEntity.ok(productService.findByUuid(uuid));
    }

}
