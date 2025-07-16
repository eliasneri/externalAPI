package com.eliasneri.externalAPI.infraestructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "product")
@Table(name = "tb_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true, nullable = false)
    private Long idOrigin;
    private String title;
    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String category;
    @Column(length = 1000)
    private String image;
}
