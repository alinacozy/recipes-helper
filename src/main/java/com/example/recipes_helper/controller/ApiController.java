package com.example.recipes_helper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.recipes_helper.DTO.ChangePasswordRequest;
import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.ProductCategory;
import com.example.recipes_helper.model.Recipe;
import com.example.recipes_helper.model.RecipeCategory;
import com.example.recipes_helper.services.RecipeService;
import com.example.recipes_helper.services.UserService;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
	private RecipeService recipeService;

	@Autowired
	private UserService userService;

    @GetMapping("/recipes") 
	public List<Recipe> getRecipes(@AuthenticationPrincipal MyUserDetails userDetails,
        @RequestParam(required = false) RecipeCategory recipeCategory, 
		@RequestParam(required = false) ProductCategory productCategory, 
		@RequestParam(required = false) boolean available){
        Long currentUserId = userDetails.getId();
		List<Recipe> listOfRecipes = recipeService.getRecipesByCategoryForUser(currentUserId, recipeCategory, productCategory, available);
		return listOfRecipes;
    }

	@PostMapping("/change_password")
    public String changePassword(@RequestBody ChangePasswordRequest request, @AuthenticationPrincipal MyUserDetails userDetails) {
		return userService.changePassword(userDetails.getId(), request.getOldPassword(), request.getNewPassword());
    }
}
