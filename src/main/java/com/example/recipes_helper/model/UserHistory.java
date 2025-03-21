package com.example.recipes_helper.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "userHistory", schema = "recipes-helper-db")
@IdClass(IdUserRecipe.class)
public class UserHistory {
    @Id
    @Column(name="user_id")
    private Long userId;

    @Id
    @Column(name="recipe_id")
    private Long recipeId;

    @Column(columnDefinition = "DATE")
    private Date date;

    @Column
    private Rating rate;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    private Recipe recipe;

    protected UserHistory() {}

    public UserHistory(Long userId, Long recipeId, Date date, Rating rate) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.date = date;
        this.rate = rate;
    }

    public Long getUserId(){
        return this.userId;
    }

    public Long getRecipeId(){
        return this.recipeId;
    }

    public Date getDate(){
        return this.date;
    }

    public Rating getRating(){
        return this.rate;
    }

}
