package com.eliasneri.externalAPI.business.services;

import com.eliasneri.externalAPI.apiv1.dto.ProductsDTO;
import com.eliasneri.externalAPI.infraestructure.entities.ProductEntity;
import com.eliasneri.externalAPI.infraestructure.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public ProductEntity saveProduct(ProductEntity entity) {
        try {

            return repository.save(entity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Products Save ERROR!");
        }
    }

    public List<ProductEntity> getAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException("getAll productEntity error!");

        }
    }

    public boolean existById(Long id) {
        return repository.existsByIdOrigin(id);
    }

    public List<ProductEntity> getByLikeName(String title) {
        return repository.getTitleByIlike(title);
    }

}
