package com.nimap.task.NimapTaskApp.controller;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.services.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategorySevice  service;


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