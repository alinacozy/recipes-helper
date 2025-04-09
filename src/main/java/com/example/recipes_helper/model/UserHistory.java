package com.example.recipes_helper.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Data
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
    @UpdateTimestamp
    // дата обновится, если пользователь повторит рецепт 
    private Date date;

    @Column
    private Rating rate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    protected UserHistory() {}

}
