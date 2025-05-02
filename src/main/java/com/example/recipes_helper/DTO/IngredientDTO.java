package com.example.recipes_helper.DTO;

import com.example.recipes_helper.model.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDTO { //информация о продукте с количеством, необходимым для рецепта, и количеством, которого не хватает пользователю
    private Long productId;
    private String productName;
    private int count; 
    private int missing; //сколько не хватает пользователю для рецепта
    private String unit;
    private ProductCategory productCategory;
}
