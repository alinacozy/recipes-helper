package com.example.recipes_helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.recipes_helper.model.IdRecipeProduct;
import com.example.recipes_helper.model.ListProduct;

public interface ListProductRepository extends CrudRepository<ListProduct, IdRecipeProduct>{
    
  @Query(value="SELECT lp.recipe_id, lp.product_id, lp.count FROM \"recipes-helper-db\".products p\n" + //
            "INNER JOIN \"recipes-helper-db\".list_product lp using(product_id)\n" + //
            "inner join \"recipes-helper-db\".recipe r using(recipe_id)\n" + //
            "where r.recipe_id = :recipeId", nativeQuery = true)
  List <ListProduct> findByRecipe (@Param("recipeId") Long recipeId);
} 