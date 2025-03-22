package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.repository.*;
import com.example.recipes_helper.services.RecipeService;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
   
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserProductRepository UserProductRepository;

    @GetMapping("/hello")
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
	  return String.format("Hello, %s!", name);
	}


	@GetMapping("/recipes")
	public String getRecipes(@RequestParam(required = false) RecipeCategory recipeCategory) {
		List<Recipe> recipes=recipeService.getRecipesByCategory(recipeCategory);
		String result="";
		for (Recipe r : recipes){
			result=result.concat(String.format("Название: %s. Категория: %s. Описание: %s.\n", r.getRecipeName(), r.getRecipeCategory(), r.getDescription()));
	  	}
		return result;
	}

	@GetMapping("/recipes/{idRecipe}")
	public String getRecipe(@PathVariable(name = "idRecipe") Long idRecipe) {
	  Recipe recipe = recipeService.getRecipeById(idRecipe);
	  return String.format("Название: %s. Категория: %s. Описание: %s.", recipe.getRecipeName(), recipe.getRecipeCategory(), recipe.getDescription());
	}


	@GetMapping("/user_products/{idUser}")
	public String getUserProductsForUser(@PathVariable(name = "idUser") Long idUser) {
		List<UserProduct> userProducts=UserProductRepository.findByUser(idUser);
		String result="";
		for (UserProduct up : userProducts){
			Product product=productRepository.findByProductId(up.getProductId());
			result=result.concat(String.format("Название: %s, количество: %s %s.\n", product.getProductName(), up.getCount(), product.getUnit()));
	  	}
		return result;
	}

	@GetMapping("/products/{idUser}")
	public String getProductsForUser(@PathVariable(name = "idUser") Long idUser) {
		List<Product> products=productRepository.findByUser(idUser);
		String result="";
		for (Product p : products){
			result=result.concat(String.format("Название: %s.\n", p.getProductName()));
	  	}
		return result;
	}
}
