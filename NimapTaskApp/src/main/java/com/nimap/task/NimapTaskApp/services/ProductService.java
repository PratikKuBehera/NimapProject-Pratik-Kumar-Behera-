package com.nimap.task.NimapTaskApp.services;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.model.Product;
import com.nimap.task.NimapTaskApp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
  The ProductService class is a service component in the Spring Boot application, responsible for
  handling the business logic related to the Product entity.
  It interacts with the ProductRepo to perform CRUD (Create, Read, Update, Delete)
  operations and supports pagination for retrieving products.

  The @Autowired annotation in Spring is used for dependency injection.
  In this case, it injects the ProductRepo bean into the ProductService class.
  This allows the ProductService to access the ProductRepo's methods, such as save(), findAll(), findById(), deleteById()
  for interacting with the database.

 */

/*
    Please move to ProductController to see the implementation.
 */

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;


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
