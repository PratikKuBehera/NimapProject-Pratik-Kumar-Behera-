package com.nimap.task.NimapTaskApp.controller;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.services.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;
import java.util.Optional;


/*
   The CategoryController class is a Spring REST controller it is responsible for exposing API endpoints related to Category entities.
   It interacts with the CategoryService to perform CRUD (Create, Read, Update, Delete) operations on Category objects and
   returns the appropriate HTTP responses.

   @Autowired is used for dependency injection in Spring, allowing automatic injection of required dependencies.
   In CategoryController, it injects CategoryService to handle business logic. This decouples the controller from the service,
   promoting better structure, flexibility, and easier testing and maintenance, with dependencies managed by the Spring context.
 */




@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    categoryService service;


    @GetMapping("/categories")
    public ResponseEntity<Page<Category>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Category> categories = service.getCategory(pageRequest);

        return ResponseEntity.ok(categories);
    }


    @PostMapping("/categories")
    public ResponseEntity<Category> createCategoryItems(@RequestBody Category category) {
        Category savedCategory = service.saveCategoryItems(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }


    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Category category = service.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with ID: " + id);
        }
    }


    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategories(@PathVariable int id, @RequestBody Category category) {
        Optional<Category> updateCategory = Optional.ofNullable(service.getCategoryById(id));

        if (updateCategory.isPresent()) {
            Category categories = updateCategory.get();
            categories.setcName(category.getcName());
            categories.setcDescription(category.getcDescription());

            Category updated = service.updateCategories(categories);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with id: " + id);
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id){

        Category category = service.getCategoryById(id);

        if(category!=null){
            service.deleteCategory(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else{
            return  new ResponseEntity<>("Category Not Found"+id , HttpStatus.NOT_FOUND);
        }
    }
}