package com.nimap.task.NimapTaskApp.repository;

import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
