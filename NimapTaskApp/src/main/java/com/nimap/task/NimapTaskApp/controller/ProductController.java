package com.nimap.task.NimapTaskApp.controller;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.model.Product;
import com.nimap.task.NimapTaskApp.repository.CategoryRepo;
import com.nimap.task.NimapTaskApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/*
   The ProductController class is responsible for handling HTTP requests related to product operations.
   It interacts with the ProductService to manage business logic and the CategoryRepo to associate products with categories.
   The class includes endpoints for creating, retrieving, updating, and deleting products.

   @Autowired is used to inject the ProductService and CategoryRepo dependencies into the controller.
   This allows Spring to automatically manage and provide the necessary service and repository instances at runtime.
   This eliminates the need for manual instantiation and ensures better code maintainability.
 */



@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    CategoryRepo categoryRepo ;


    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Product> products = service.getProduct(pageable);
        return ResponseEntity.ok(products);
    }


    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {

        Category category = categoryRepo.findById(product.getCategory().getcId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        Product savedProduct = service.saveProductItems(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Optional<Product> productOptional = Optional.ofNullable(service.getProductById(id));
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is not present for product ID: " + id);
        }
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateCategories(@PathVariable int id, @RequestBody Product product) {
        Optional<Product> updateCategory = Optional.ofNullable(service.getProductById(id));

        if (updateCategory.isPresent()) {
            Product products = updateCategory.get();
            products.setpName(product.getpName());
            products.setpDescription(product.getpDescription());
            products.setpPrice(product.getpPrice());
            products.setpQuantity(product.getpQuantity());

            Product updated = service.updateProduct(product);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found with id: " + id);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){

        Product category = service.getProductById(id);

        if(category!=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>("Product Not Found"+id , HttpStatus.NOT_FOUND);
        }
    }
}
