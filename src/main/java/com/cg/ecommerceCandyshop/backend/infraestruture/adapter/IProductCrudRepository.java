package com.cg.ecommerceCandyshop.backend.infraestruture.adapter;

import com.cg.ecommerceCandyshop.backend.domain.model.Product;
import com.cg.ecommerceCandyshop.backend.infraestruture.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
