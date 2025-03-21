package com.example.recipes_helper.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class IdUserRecipe implements Serializable {
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    protected Long userId;

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="recipe_id")
    protected Long recipeId;

    public IdUserRecipe() {}

    public IdUserRecipe(Long recipeId, Long userId) {
        this.recipeId = recipeId;
        this.userId = userId;
    }

    public Long getRecipeId(){
        return this.recipeId;
    }

    public Long getUserId(){
        return this.userId;
    }

    @Override
    public boolean equals(Object otherOb) {
        if (this == otherOb) {return true;}
        if (!(otherOb instanceof IdUserRecipe)) {return false;}
        IdUserRecipe other = (IdUserRecipe) otherOb;
        return ((recipeId==null ? other.recipeId==null :
            recipeId.equals(other.recipeId)) && (userId.equals(other.userId)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, userId);
    }

}
