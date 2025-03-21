package com.example.recipes_helper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "listProduct", schema = "recipes-helper-db")
@IdClass(IdRecipeProduct.class)
public class ListProduct {
    // составной ключ
    @Id
    @Column(name="recipe_id")
    private Integer recipeId;

    @Id
    @Column(name="product_id")
    private Integer productId;

    @Column
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected ListProduct() {}

    public ListProduct(Integer recipeId, Integer productId,  Integer count) {
        this.recipeId = recipeId;
        this.productId = productId;
        this.count = count;
    }

    public Integer getRecipeId(){
        return this.recipeId;
    }

    public Integer getProductId(){
        return this.productId;
    }

    public Integer getCount(){
        return this.count;
    }

}
