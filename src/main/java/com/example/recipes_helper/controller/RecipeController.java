package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.recipes_helper.DTO.RecipeWithIngredientsDTO;
import com.example.recipes_helper.DTO.UserHistoryDTO;
import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.ProductCategory;
import com.example.recipes_helper.model.Rating;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.services.HistoryService;
import com.example.recipes_helper.services.RecipeService;

@Controller
public class RecipeController {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private HistoryService historyService;

    @GetMapping("/hello")
	@ResponseBody
	public String getHello(@RequestParam(defaultValue = "Alina and Sofia") String name) {
	  return String.format("Hello, %s!", name);
	}

	@GetMapping("/recipes") // все рецепты, здесь в параметрах передаются фильтры
	public String getRecipes(@AuthenticationPrincipal MyUserDetails userDetails, Model model, 
		@RequestParam(required = false) RecipeCategory recipeCategory, 
		@RequestParam(required = false) ProductCategory productCategory, 
		@RequestParam(required = false) boolean available) {
		Long currentUserId = userDetails.getId();
		List<Recipe> listOfRecipes = recipeService.getRecipesByCategoryForUser(currentUserId, recipeCategory, productCategory, available);
		model.addAttribute("recipes", listOfRecipes);
		return "recipes.html";
	}

	@GetMapping("/recipes/{idRecipe}") // конкретный рецепт с ингредиентами
	public String getRecipe(@AuthenticationPrincipal MyUserDetails userDetails, @PathVariable Long idRecipe, Model model) {
		Long currentUserId = userDetails.getId();
		RecipeWithIngredientsDTO recipeWithIngredients = recipeService.getRecipeWithIngredientsById(idRecipe, currentUserId);
		model.addAttribute("recipe", recipeWithIngredients);
		return "recipe.html";
	}

	@GetMapping("/user_recipes")
	@ResponseBody
	public List<Recipe> getRecipesForCurrentUser(@AuthenticationPrincipal MyUserDetails userDetails, @RequestParam(required = false) RecipeCategory recipeCategory, @RequestParam(required = false) ProductCategory productCategory) {
		Long currentUserId = userDetails.getId();
		return recipeService.getRecipesForUser(currentUserId, recipeCategory, productCategory);
	}

//	@PostMapping("/recipes/cook")
//	public String cookRecipe(@AuthenticationPrincipal MyUserDetails userDetails, @RequestParam Long recipeId,
//	Model model) {
//		Long currentUserId = userDetails.getId();
//		recipeService.decreaseProducts(recipeId, currentUserId);
//		historyService.saveCookedRecipe(currentUserId, recipeId);
//
//		model.addAttribute("alertMessage", "Рецепт успешно приготовлен!");
//
//		return "redirect:/recipe.html";
//	}

	@PostMapping("/recipes/cook")
	public String cookRecipe(@AuthenticationPrincipal MyUserDetails userDetails,
							 @RequestParam Long recipeId,
							 RedirectAttributes redirectAttributes) {
		Long currentUserId = userDetails.getId();
		recipeService.decreaseProducts(recipeId, currentUserId);
		historyService.saveCookedRecipe(currentUserId, recipeId);

		// Добавляем flash-сообщение - оно сохранится только на время следующего запроса
		redirectAttributes.addFlashAttribute("successMessage", "Рецепт успешно приготовлен!");
		return "redirect:/recipes/" + recipeId;
	}


//	@GetMapping("/recipe_success")
//	public String showSuccesPage(@AuthenticationPrincipal MyUserDetails userDetails, @RequestParam Long recipeId, Model model) {
//		UserHistoryDTO recipe = historyService.findHistoryByUserAndRecipe(userDetails.getId(), recipeId);
//		model.addAttribute("recipe", recipe);
//		model.addAttribute("ratings", Rating.values());
//		return "recipe_success.html";
//	}

	
}
