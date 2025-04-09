//package com.example.recipes_helper.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.recipes_helper.model.Recipe;
//import com.example.recipes_helper.model.RecipeCategory;
//import com.example.recipes_helper.repository.RecipeRepository;
//
//@Service
//public interface RecipeService {
//    Recipe getRecipeById(Long idRecipe);
//    List<Recipe> getRecipesByCategory(RecipeCategory recipeCategory);
//
//
//}
package com.example.recipes_helper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.repository.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    public Recipe getRecipeById(Long idRecipe){
        return repository.findByRecipeId(idRecipe);
    }

    public List<Recipe> getRecipesByCategory(RecipeCategory recipeCategory){
        if (recipeCategory!=null){ //если значение параметра пришло (не null)
            return (List<Recipe>) repository.findByRecipeCategory(recipeCategory);
        }
        //если параметр не пришел:
        return (List<Recipe>) repository.findAll();
    }
}