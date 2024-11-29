package com.nimap.task.NimapTaskApp.services;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.model.Product;
import com.nimap.task.NimapTaskApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    // This Service Pages:

    public Page<Product> getProduct(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Product saveProductItems(Product category){

        return repo.save(category);
    }

    public Product getProductById(int Id){
        return repo.findById(Id);
    }

    public Product updateProduct(Product product) {
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}
