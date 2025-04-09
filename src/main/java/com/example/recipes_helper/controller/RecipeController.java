package com.example.recipes_helper.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.model.ListProduct;
import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.repository.ListProductRepository;
import com.example.recipes_helper.repository.ProductRepository;
import com.example.recipes_helper.repository.RecipeRepository;
import com.example.recipes_helper.repository.UserProductRepository;
import com.example.recipes_helper.services.RecipeService;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
   
	@Autowired
	private RecipeRepository recipeRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserProductRepository userProductRepository;

	@Autowired
	private ListProductRepository listProductRepository;

    @GetMapping("/hello")
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
	  return String.format("Hello, %s!", name);
	}

	@GetMapping("/recipes") // все рецепты, здесь в параметрах передаются фильтры
	public List<Recipe> getRecipes(@RequestParam(required = false) RecipeCategory recipeCategory) {
		return recipeService.getRecipesByCategory(recipeCategory);
	}
//
//	@GetMapping("/recipes/{idRecipe}") // конкретный рецепт
//	public Recipe getRecipe(@PathVariable(name = "idRecipe") Long idRecipe) {
//		Recipe recipe = recipeService.getRecipeById(idRecipe);
//		if (recipe == null) {
//			// обработка ситуации, когда рецепт не найден
//			return null;
//		}
//
//		List<ListProduct> listProducts = listProductRepository.findByRecipe(idRecipe);
//		List<Product> products = new ArrayList<>();
//		for (ListProduct lp : listProducts) {
//			Product product = productRepository.findByProductId(lp.getProductId());
//			products.add(product);
//		}
//		return recipe;
//	}
//
//	@GetMapping("/user_recipes/{idUser}")
//	public List<Recipe> getRecipesForUser(@PathVariable(name = "idUser") Long idUser) {
//		List<Recipe> availableRecipes = new ArrayList<>();
//		Iterable<Recipe> allRecipes = recipeRepository.findAll();
//		for (Recipe recipe : allRecipes) {
//			List<ListProduct> listProducts = listProductRepository.findByRecipe(recipe.getRecipeId());
//			boolean canPrepare = true;
//			for (ListProduct lp : listProducts) {
//				UserProduct userProduct = userProductRepository.findByUserAndProduct(idUser, lp.getProductId());
//				if (userProduct == null || userProduct.getCount() < lp.getCount()) {
//					canPrepare = false;
//					break;
//				}
//			}
//			if (canPrepare) {
//				availableRecipes.add(recipe);
//			}
//		}
//		return availableRecipes;
//	}

	// @PostMapping("/recipes/{id_recipe}")
	
}
