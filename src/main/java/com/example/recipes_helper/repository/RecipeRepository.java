package com.example.recipes_helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{
  List<Recipe> findByRecipeName(String recipeName);

  List <Recipe> findByRecipeCategory(RecipeCategory recipeCategory);

  Recipe findByRecipeId(Long recipeId);
    
  @Query(value="select r.recipe_id, r.recipe_name, r.recipe_category, r.description from \"recipes-helper-db\".recipe r\n" + //
        "where (SELECT count(*) FROM \"recipes-helper-db\".user_product up\n" + //
        "inner join \"recipes-helper-db\".users u using(user_id)\n" + //
        "inner join \"recipes-helper-db\".list_product lp using(product_id)\n" + //
        "where u.user_id = :userId and up.count>=lp.count and lp.recipe_id = r.recipe_id ) =\n" + //
        "(SELECT count(*) FROM \"recipes-helper-db\".list_product lp \n" + //
        "where lp.recipe_id = r.recipe_id)", nativeQuery = true)
  List <Recipe> findByUser(@Param("userId") Long userId);
} 

