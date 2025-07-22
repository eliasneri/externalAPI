package com.eliasneri.externalAPI.infraestructure.services;

import com.eliasneri.externalAPI.apiv1.dto.ProductsDTO;
import com.eliasneri.externalAPI.infraestructure.converter.ProductConverter;
import com.eliasneri.externalAPI.infraestructure.entities.ProductEntity;
import com.eliasneri.externalAPI.infraestructure.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductConverter converter;

    @Transactional()
    public ProductEntity saveProduct(ProductEntity entity) {
        try {

            return repository.save(entity);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Products Save ERROR!");
        }
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getAll() {
        try {
            List<ProductEntity> list = repository.findAll();
            return list;
        } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException("getAll productEntity error!");

        }
    }

    @Transactional(readOnly = true)
    public boolean existById(Long id) {
        return repository.existsByIdOrigin(id);
    }

    @Transactional(readOnly = true)
    public List<ProductsDTO> getByLikeTitle(String title) {
        List<ProductEntity> list = repository.getTitleByIlike(title);
        return converter.toListDTO(list);
    }

    @Transactional()
    public String deleteByUuid (String uuid) {
        if (repository.existsById(uuid)) {
            try {
                repository.deleteById(uuid);
                return "Delete sucessfully";
            } catch (DataIntegrityViolationException e){
                e.printStackTrace();
            }
        }

        return "non delete";
    }

    public ProductsDTO findByUuid(String uuid) {
        Optional<ProductEntity> entity = repository.findById(uuid);
        return converter.toDTO(entity.get());
    }
}
