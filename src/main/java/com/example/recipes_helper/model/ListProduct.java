package com.example.recipes_helper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "listProduct", schema = "recipes-helper-db")
@IdClass(IdRecipeProduct.class)
public class ListProduct {
    // составной ключ
    @Id
    @Column(name="recipe_id")
    private Long recipeId;

    @Id
    @Column(name="product_id")
    private Long productId;

    @Column
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected ListProduct() {}

}
