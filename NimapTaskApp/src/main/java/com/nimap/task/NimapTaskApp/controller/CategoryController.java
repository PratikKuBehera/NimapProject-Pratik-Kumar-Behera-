package com.nimap.task.NimapTaskApp.controller;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.services.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategorySevice  service;

    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return service.getCategory();
    }

    @PostMapping("/categories")
    public void createCategoryItems(@RequestBody Category category){
        service.saveCategoryItems(category);
    }

    @GetMapping("/categories/{id}")
    public Category getById(@PathVariable int id){
        return service.getCategoryById(id);
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