package com.nimap.task.NimapTaskApp.services;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/*
   The CategoryService class provides business logic for managing Category entities.
   It interacts with the CategoryRepo repository to perform CRUD operations like
   creating, retrieving, updating, and deleting categories.
   The service also supports pagination for retrieving category data efficiently.

    @Autowired is an annotation used for dependency injection.
    It tells Spring to automatically inject the required bean (CategoryRepo repository) into the class.
    Spring will look for a bean of type CategoryRepo in the application context and inject it into the repo field.
    This allows the categoryService to access the ProductRepo's methods, such as save(), findAll(), findById(), deleteById()
    for interacting with the database.
 */
  /*
    Please move to CategoryController to see the implementation.
   */

@Service
public class categoryService {

    @Autowired
    CategoryRepo repo;

    public Page<Category> getCategory(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Category saveCategoryItems(Category category){
        return repo.save(category);
    }

    public Category getCategoryById(int Id){
        return repo.findById(Id).get();
    }

    public Category updateCategories(Category category) {
        return repo.save(category);
    }

    public void deleteCategory(int id) {
        repo.deleteById(id);
    }
}
