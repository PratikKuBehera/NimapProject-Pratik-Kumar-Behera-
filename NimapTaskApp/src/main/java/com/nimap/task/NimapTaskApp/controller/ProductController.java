package com.nimap.task.NimapTaskApp.controller;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.model.Product;
import com.nimap.task.NimapTaskApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;


    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getProduct();
    }

    @PostMapping("/products")
    public String createProductItems(@RequestBody Product product){
       return service.saveProductItems(product);
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
