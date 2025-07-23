package com.eliasneri.externalAPI.application.converter;

import com.eliasneri.externalAPI.domain.dto.ProductsDTO;
import com.eliasneri.externalAPI.domain.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConverter {

    public ProductEntity toEntity(ProductsDTO dto) {
        return ProductEntity.builder()
                .idOrigin(dto.getId())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .image(dto.getImage())
                .price(dto.getPrice())
                .build();
    }

    public ProductsDTO toDTO(ProductEntity entity) {
        return ProductsDTO.builder()
                .entityId(entity.getId().toString())
                .id(entity.getIdOrigin())
                .title(entity.getTitle())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .image(entity.getImage())
                .build();
    }

    public List<ProductsDTO> toListDTO(List<ProductEntity> entityList) {
        return entityList.stream().map(x -> toDTO(x)).toList();
    }


}
