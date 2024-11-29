package com.nimap.task.NimapTaskApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nimap.task.NimapTaskApp.model.Category;
import com.nimap.task.NimapTaskApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

      Page<Category> findAll(Pageable pageable);
}
