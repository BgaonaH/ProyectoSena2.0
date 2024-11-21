package com.cg.ecommerceCandyshop.backend.infraestruture.adapter;

import com.cg.ecommerceCandyshop.backend.infraestruture.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
}
