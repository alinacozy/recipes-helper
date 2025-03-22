package com.example.recipes_helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.ProductCategory;

public interface ProductRepository extends CrudRepository<Product, Long>{
  List<Product> findByProductName(String productName);

  List <Product> findByProductCategory(ProductCategory productCategory);

  Product findByProductId(Long productId);
    
  @Query(value="SELECT p.product_id, p.product_name, p.unit, p.product_category FROM \"recipes-helper-db\".products p\n" + //
            "INNER JOIN \"recipes-helper-db\".user_product up using(product_id)\n" + //
            "inner join \"recipes-helper-db\".users u using(user_id)\n" + //
            "where u.user_id = :userId and up.count>0", nativeQuery = true)
  List <Product> findByUser(@Param("userId") Long userId);
} 