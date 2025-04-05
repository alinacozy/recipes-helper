package com.example.recipes_helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.recipes_helper.model.IdUserProduct;
import com.example.recipes_helper.model.UserProduct;

public interface UserProductRepository extends CrudRepository<UserProduct, IdUserProduct>{
    
  @Query(value="SELECT up.user_id, up.product_id, up.count FROM \"recipes-helper-db\".products p\n" + //
            "INNER JOIN \"recipes-helper-db\".user_product up using(product_id)\n" + //
            "inner join \"recipes-helper-db\".users u using(user_id)\n" + //
            "where u.user_id = :userId and up.count>0", nativeQuery = true)
  List <UserProduct> findByUser(@Param("userId") Long userId);

  @Query("SELECT up FROM UserProduct up WHERE up.user.id = :userId AND up.product.id = :productId")
  UserProduct findByUserAndProduct(@Param("userId") Long userId, @Param("productId") Long productId);
} 