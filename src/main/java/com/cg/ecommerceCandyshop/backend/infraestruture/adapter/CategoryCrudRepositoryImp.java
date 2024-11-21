package com.cg.ecommerceCandyshop.backend.infraestruture.adapter;

import com.cg.ecommerceCandyshop.backend.domain.model.Category;
import com.cg.ecommerceCandyshop.backend.domain.port.ICategoryRepository;
import com.cg.ecommerceCandyshop.backend.infraestruture.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryCrudRepositoryImp implements ICategoryRepository {
    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCrudRepositoryImp(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory( iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category))) ;
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategoryList(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).orElseThrow(
        ()-> new RuntimeException("Categoría con id: "+id+ " no existe")));
    }

    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Categoría con id: "+id+ " no existe")
        );
        iCategoryCrudRepository.deleteById(id);
    }
}
