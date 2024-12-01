package com.nimap.task.NimapTaskApp.services;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySevice {

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
