package com.example.recipes_helper.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipe", schema = "recipes-helper-db")
public class Recipe {

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="recipe_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
    @Column(name="recipe_id")
    private Long recipeId;

    @Column(name="recipe_name")
    private String recipeName;

    @Column(name="description")
    private String description;

    @Column(name="recipe_category")
    private RecipeCategory recipeCategory;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserHistory> userRecipes;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListProduct> listProducts;

    

    protected Recipe() {}

    public Recipe(String recipeName, String description, RecipeCategory recipeCategory) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeCategory = recipeCategory;
    }

    public Long getRecipeId(){
        return this.recipeId;
    }

    public String getRecipeName(){
        return this.recipeName;
    }

    public String getDescription(){
        return this.description;
    }

    public RecipeCategory getRecipeCategory(){
        return this.recipeCategory;
    }

}
