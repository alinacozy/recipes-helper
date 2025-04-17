package com.example.recipes_helper.DTO;

import com.example.recipes_helper.model.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDTO {
    private Long productId;
    private String productName;
    private int count; 
    private String unit;
    private ProductCategory productCategory;
}
