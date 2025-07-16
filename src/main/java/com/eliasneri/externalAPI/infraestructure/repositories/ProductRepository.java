package com.eliasneri.externalAPI.infraestructure.repositories;

import com.eliasneri.externalAPI.infraestructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    Boolean existsByIdOrigin(Long idOrigin);

}
