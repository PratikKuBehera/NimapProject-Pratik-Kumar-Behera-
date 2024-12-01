package com.nimap.task.NimapTaskApp.repository;

import com.nimap.task.NimapTaskApp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
   This @Repository ProductRepo interface which is extending the JpaRepository
   to get all the inbuilt methods to perform CRUD operation in Product.
   Some of builtin methods that has been used here to achieve the project
   requirements are :
   * findAll() --> This is used to get all the recodes from the database.
   * save() --> This is used to save a new records is inserted.
   * findById() --> This is used to find a specific category on the bases of Id.
   * deleteById() --> This method is used to perform delete operation on the bases of Id.

   And save() method is also used to update a particular category  on the bases of Id.

   These are some operations which are perform to achieve the goal of the project.
 */
/*
   Please move to the service class( ProductService ) to see the implementation of all  the methods.
 */



@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Product findById(int id);

    Page<Product> findAll(Pageable pageable);

}
