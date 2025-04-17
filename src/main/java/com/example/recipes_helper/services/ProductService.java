package com.example.recipes_helper.services;

import java.util.List;

import com.example.recipes_helper.DTO.IngredientDTO;
import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.UserProduct;

public interface ProductService {
    List<IngredientDTO> findAllProductsByUser(Long  userId);
    UserProduct saveProduct(UserProduct product);
    UserProduct updateProduct(UserProduct product);
    List<Product> findAllProducts();
}
