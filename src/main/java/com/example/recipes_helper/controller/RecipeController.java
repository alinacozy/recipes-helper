package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.model.ListProduct;
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
	public String getRecipes(@RequestParam(required = false) RecipeCategory recipeCategory) {
		List<Recipe> recipes=recipeService.getRecipesByCategory(recipeCategory);
		String result="";
		for (Recipe r : recipes){
			result=result.concat(String.format("Название: %s. Категория: %s. Описание: %s<br>\n", r.getRecipeName(), r.getRecipeCategory(), r.getDescription()));
		}
		return result;
	}

	@GetMapping("/recipes/{idRecipe}") // конкретный рецепт
	public String getRecipe(@PathVariable(name = "idRecipe") Long idRecipe) {
	  	Recipe recipe = recipeService.getRecipeById(idRecipe);
	  	String result=String.format("Название: %s. Категория: %s. Описание: %s<br> Ингридиенты:<br>", recipe.getRecipeName(), recipe.getRecipeCategory(), recipe.getDescription());
	  	List<ListProduct> listProducts=listProductRepository.findByRecipe(idRecipe);
	  	for (ListProduct lp : listProducts){
			Product product=productRepository.findByProductId(lp.getProductId());
			result=result.concat(String.format("Название: %s, %s %s<br>\n", product.getProductName(), lp.getCount(), product.getUnit()));
	  
		}
	  return result;
	}


	@GetMapping("/products/{idUser}") //продукты в наличии у конкретного пользователя
	public String getProductsForUser(@PathVariable(name = "idUser") Long idUser) {
		List<UserProduct> userProducts=userProductRepository.findByUser(idUser);
		String result="";
		for (UserProduct up : userProducts){
			Product product=productRepository.findByProductId(up.getProductId());
			result=result.concat(String.format("Название: %s, %s %s<br>\n", product.getProductName(), up.getCount(), product.getUnit()));
	  	}
		return result;
	}

	@GetMapping("/user_recipes/{idUser}") // рецепты, которые может приготовить пользователь исходя из кол-ва его продуктов
	public String getRecipesForUser(@PathVariable(name = "idUser") Long idUser) {
		List<Recipe> recipes=recipeRepository.findByUser(idUser);
		String result="";
		for (Recipe r : recipes){
			result=result.concat(String.format("Название: %s. Категория: %s. Описание: %s<br>Ингридиенты:<br>\n", r.getRecipeName(), r.getRecipeCategory(), r.getDescription()));
			List<ListProduct> listProducts=listProductRepository.findByRecipe(r.getRecipeId());
	  		for (ListProduct lp : listProducts){
				Product product=productRepository.findByProductId(lp.getProductId());
				result=result.concat(String.format("Название: %s, %s %s<br>\n", product.getProductName(), lp.getCount(), product.getUnit()));
	  		}
		}
		return result;
	}

}
