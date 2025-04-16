package com.example.recipes_helper.DTO;

import com.example.recipes_helper.model.Rating;

import lombok.Data;

@Data
public class UserHistoryRequest {
    private Long recipeId;
    private Rating rating;
}
