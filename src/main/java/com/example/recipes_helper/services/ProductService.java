package com.example.recipes_helper.services;

import java.util.List;

import com.example.recipes_helper.model.UserProduct;

public interface ProductService {
    List<UserProduct> findAllProductsByUser(Long  userId);
    UserProduct saveProduct(UserProduct product);
    UserProduct updateProduct(UserProduct product);

}
