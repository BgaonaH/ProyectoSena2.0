package com.cg.ecommerceCandyshop.backend.infraestruture.config;

import com.cg.ecommerceCandyshop.backend.application.*;
import com.cg.ecommerceCandyshop.backend.domain.port.ICategoryRepository;
import com.cg.ecommerceCandyshop.backend.domain.port.IOrderRepository;
import com.cg.ecommerceCandyshop.backend.domain.port.IProductRepository;
import com.cg.ecommerceCandyshop.backend.domain.port.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public UserService userService(IUserRepository iUserRepository){
        return new UserService(iUserRepository);
    }

    @Bean
    public CategoryService categoryService(ICategoryRepository iCategoryRepository){
        return new CategoryService(iCategoryRepository);
    }
    @Bean
    public ProductService productService(IProductRepository iProductRepository, UploadFile uploadFile){
        return  new ProductService(iProductRepository, uploadFile);
    }
    @Bean
    public OrderService orderService(IOrderRepository iOrderRepository){
        return new OrderService(iOrderRepository);
    }
    @Bean
    public UploadFile uploadFile(){
        return new UploadFile();
    }
    @Bean
    public RegistrationService registrationService(IUserRepository iUserRepository){
        return new RegistrationService(iUserRepository);
    }

}