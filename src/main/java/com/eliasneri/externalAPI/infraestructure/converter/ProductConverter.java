package com.eliasneri.externalAPI.infraestructure.converter;

import com.eliasneri.externalAPI.apiv1.dto.ProductsDTO;
import com.eliasneri.externalAPI.infraestructure.entities.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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
