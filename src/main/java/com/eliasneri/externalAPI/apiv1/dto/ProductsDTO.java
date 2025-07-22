package com.eliasneri.externalAPI.apiv1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
