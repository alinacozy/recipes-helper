package com.example.recipes_helper.repository;

import com.example.recipes_helper.model.UserProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class ProductDAO {
    private final List<UserProduct> USER_PRODUCTS = new ArrayList<>();
    public List<UserProduct> findAllProductsByUser(Long userId) {
        return USER_PRODUCTS.stream()
                .filter(product -> product.getUserId().equals(userId))
                .collect(Collectors.toList());
    };
    public UserProduct saveProduct(UserProduct product){
        USER_PRODUCTS.add(product);
        return product;
    };

    public UserProduct updateProduct(UserProduct product){
        var productIndex = IntStream.range(0, USER_PRODUCTS.size())
                .filter(index -> USER_PRODUCTS.get(index).getProductId().equals(product.getProductId()))
                .findFirst()
                .orElse(-1);
        if(productIndex>-1) {
            USER_PRODUCTS.set(productIndex, product);
            return product;
        }
        return null;
    };

}
