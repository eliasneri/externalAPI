package com.eliasneri.externalAPI.domain.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class ProductsDTO {

    private Long id;
    private String entityId;
    private String title;
    private BigDecimal price;
    private String description;
    private String category;
    private String image;

}
