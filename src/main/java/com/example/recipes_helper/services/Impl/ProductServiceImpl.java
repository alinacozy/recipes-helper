package com.example.recipes_helper.services.Impl;

import com.example.recipes_helper.DTO.ProductDTO;
import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.repository.ProductRepository;
import com.example.recipes_helper.repository.UserProductRepository;
import com.example.recipes_helper.services.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final UserProductRepository userProductRepository;
    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> findAllProductsByUser(Long userId) {
        List<UserProduct> userProducts = userProductRepository.findAllByUserId(userId);
        List<ProductDTO> userIngredients = new ArrayList<>();
        for (UserProduct up : userProducts){
            Product product = up.getProduct();
            userIngredients.add(new ProductDTO(product.getProductId(), product.getProductName(), up.getCount(), product.getUnit(), product.getProductCategory()));
        }
        return userIngredients;
    }

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }


    @Override
    public UserProduct saveProduct(UserProduct product) {
        Optional<UserProduct> existingUserProduct = userProductRepository.findByUserIdAndProductId(product.getUserId(), product.getProductId());
        if (existingUserProduct.isPresent()) {
            UserProduct existing = existingUserProduct.get();
            existing.setCount(existing.getCount() + product.getCount());
            return userProductRepository.save(existing);
        }
        return userProductRepository.save(product);

    }

    @Override
    public UserProduct updateProduct(UserProduct product) {
        Optional<UserProduct> existingUserProduct = userProductRepository.findByUserIdAndProductId(product.getUserId(), product.getProductId());
        if (existingUserProduct.isPresent()) {
            UserProduct existing = existingUserProduct.get();
            existing.setCount(product.getCount());
            return userProductRepository.save(existing);
        }
        return null;
    }

    @Override
    public Product getProductById(Long productId){
        return productRepository.findByProductId(productId);
    }
}