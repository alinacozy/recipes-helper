package com.example.recipes_helper.DTO;

import java.time.LocalDate;

import com.example.recipes_helper.model.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserHistoryDTO {
    Long recipeId;
    String recipeName;
    LocalDate date;
    Rating rate;
}
