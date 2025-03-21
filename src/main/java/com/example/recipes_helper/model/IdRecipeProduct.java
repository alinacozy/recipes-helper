package com.example.recipes_helper.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class IdRecipeProduct implements Serializable {

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="recipe_id")
    protected Long recipeId;

    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="product_id")
    protected Long productId;

    public IdRecipeProduct() {}

    public IdRecipeProduct(Long recipeId, Long productId) {
        this.recipeId = recipeId;
        this.productId = productId;
    }

    public Long getRecipeId(){
        return this.recipeId;
    }

    public Long getProductId(){
        return this.productId;
    }

    @Override
    public boolean equals(Object otherOb) {
        if (this == otherOb) {return true;}
        if (!(otherOb instanceof IdRecipeProduct)) {return false;}
        IdRecipeProduct other = (IdRecipeProduct) otherOb;
        return ((recipeId==null ? other.recipeId==null :
            recipeId.equals(other.recipeId)) && (productId.equals(other.productId)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, productId);
    }

}
