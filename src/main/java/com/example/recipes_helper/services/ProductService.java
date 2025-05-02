package com.example.recipes_helper.services;

import java.util.List;

import com.example.recipes_helper.DTO.ProductDTO;
import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.UserProduct;

public interface ProductService {
    List<ProductDTO> findAllProductsByUser(Long  userId);
    UserProduct saveProduct(UserProduct product);
    UserProduct updateProduct(UserProduct product);
    List<Product> findAllProducts();
}
