package com.example.recipes_helper.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class IdUserRecipe implements Serializable {

    @Column(name="user_id")
    protected Long userId;


    @Column(name="recipe_id")
    protected Long recipeId;

    public IdUserRecipe() {}

}
