package com.nimap.task.NimapTaskApp.services;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.model.Product;
import com.nimap.task.NimapTaskApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;
    // This Service Pages
    public List<Product> getProduct(){
        return repo.findAll();
    }
    public String saveProductItems(Product category){
                repo.save(category);
                return "success";
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
