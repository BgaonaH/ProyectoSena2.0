package com.cg.ecommerceCandyshop.backend.infraestruture.rest;


import com.cg.ecommerceCandyshop.backend.application.ProductService;
import com.cg.ecommerceCandyshop.backend.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/admin/products")
@Slf4j
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestParam("id") Integer id,
                                        @RequestParam("code") String code,
                                        @RequestParam("name") String name,
                                        @RequestParam("description") String  description,
                                        @RequestParam("price")BigDecimal price,
                                        @RequestParam("urlImage")String urlImage,
                                        @RequestParam("userId")Integer userId,
                                        @RequestParam("categoryId")Integer categoryId,
                                        @RequestParam(value = "image", required = false)MultipartFile multipartFile
    ) throws IOException {
        Product product = new Product();
        if (id == null || id == 0) { // Crear nuevo producto
            product = new Product(); product.setDateCreated(LocalDateTime.now()); // Asignar fecha de creación
        } else { // Recuperar producto existente para actualizar
            product = productService.findById(id); log.info("Fecha de creación original: {}", product.getDateCreated());
            // Preservar dateCreated y asignar dateUpdated
            if (product.getDateCreated() == null){
                product.setDateCreated(LocalDateTime.now());
            }
            product.setDateUpdated(LocalDateTime.now()); }
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        product.setUserId(userId);
        product.setUrlImage(urlImage);


        log.info("NOmbre producto: {}", product.getName());
        return  new ResponseEntity<>(productService.save(product, multipartFile), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id){
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        List<Product> products = productService.searchByName(query);
        return ResponseEntity.ok(products);
    }
}