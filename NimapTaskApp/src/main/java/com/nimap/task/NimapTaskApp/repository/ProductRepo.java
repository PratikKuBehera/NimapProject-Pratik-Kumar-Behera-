package com.nimap.task.NimapTaskApp.repository;

import com.nimap.task.NimapTaskApp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Product findById(int id);

    Page<Product> findAll(Pageable pageable);

}
