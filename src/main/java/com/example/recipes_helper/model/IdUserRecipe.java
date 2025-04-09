package com.example.recipes_helper.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
public class IdUserRecipe implements Serializable {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    protected Long userId;

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="recipe_id")
    protected Long recipeId;

    public IdUserRecipe() {}

}
