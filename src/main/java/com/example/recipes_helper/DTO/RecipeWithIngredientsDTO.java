package com.example.recipes_helper.DTO;

import java.util.List;

import com.example.recipes_helper.model.RecipeCategory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeWithIngredientsDTO {
    private Long recipeId;
    private String name;
    private String description;
    private RecipeCategory category;
    private List<IngredientDTO> ingredients;
}
