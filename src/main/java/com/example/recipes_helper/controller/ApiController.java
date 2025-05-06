package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.ProductCategory;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.services.RecipeService;


@RestController
public class ApiController {

    @Autowired
	private RecipeService recipeService;

    @GetMapping("/api/recipes") 
	public List<Recipe> getRecipes(@AuthenticationPrincipal MyUserDetails userDetails,
        @RequestParam(required = false) RecipeCategory recipeCategory, 
		@RequestParam(required = false) ProductCategory productCategory, 
		@RequestParam(required = false) boolean available){
        Long currentUserId = userDetails.getId();
		List<Recipe> listOfRecipes = recipeService.getRecipesByCategoryForUser(currentUserId, recipeCategory, productCategory, available);
		return listOfRecipes;
    }
}
