package com.cg.ecommerceCandyshop.backend.application;

import com.cg.ecommerceCandyshop.backend.domain.model.Category;
import com.cg.ecommerceCandyshop.backend.domain.port.ICategoryRepository;

public class CategoryService {
    private final ICategoryRepository iCategoryRepository;

    public CategoryService(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }
    public Category save (Category category){
        return iCategoryRepository.save(category);
    }
    public Iterable<Category> findAll(){
        return iCategoryRepository.findAll();
    }

    public Category findById(Integer id){
        return iCategoryRepository.findById(id);
    }
    public void deleteById(Integer id){
        iCategoryRepository.deleteById(id);
    }


}
