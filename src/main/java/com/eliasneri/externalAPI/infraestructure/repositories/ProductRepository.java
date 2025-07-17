package com.eliasneri.externalAPI.infraestructure.repositories;

import com.eliasneri.externalAPI.infraestructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    Boolean existsByIdOrigin(Long idOrigin);

    @Query(value = "SELECT * FROM tb_product where unaccent(LOWER(title)) LIKE unaccent(LOWER(CONCAT('%', :titleFind, '%')))", nativeQuery = true )
    List<ProductEntity> getTitleByIlike(@Param("titleFind") String title);

}
