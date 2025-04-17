package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.model.UserHistory;
import com.example.recipes_helper.services.HistoryService;
import com.example.recipes_helper.services.RecipeService;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private HistoryService historyService;

    @GetMapping("/hello")
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
	  return String.format("Hello, %s!", name);
	}

	@GetMapping("/recipes") // все рецепты, здесь в параметрах передаются фильтры
	public List<Recipe> getRecipes(@RequestParam(required = false) RecipeCategory recipeCategory) {
		return recipeService.getRecipesByCategory(recipeCategory);
	}

	@GetMapping("/recipes/{idRecipe}") // конкретный рецепт
	public Recipe getRecipe(@PathVariable(name = "idRecipe") Long idRecipe) {
		Recipe recipe = recipeService.getRecipeById(idRecipe);
		if (recipe == null) {
			// обработка ситуации, когда рецепт не найден
			return null;
		}

		// List<ListProduct> listProducts = listProductRepository.findByRecipe(idRecipe);
		// List<Product> products = new ArrayList<>();
		// for (ListProduct lp : listProducts) {
		// 	Product product = productRepository.findByProductId(lp.getProductId());
		// 	products.add(product);
		// }
		return recipe;
	}

	@GetMapping("/user_recipes/{idUser}")
	public List<Recipe> getRecipesForUser(@PathVariable Long idUser) {
		return recipeService.getRecipesForUser(idUser);
	}

	@PostMapping("/recipes/{idRecipe}/{idUser}")
	public UserHistory cookRecipe(@PathVariable Long idRecipe, @PathVariable Long idUser){
		recipeService.decreaseProducts(idRecipe, idUser);
		UserHistory savedHistory=historyService.saveCookedRecipe(idUser, idRecipe);
		return savedHistory;
	}
	
}
