package com.example.recipes_helper.services.Impl;

import com.example.recipes_helper.model.Product;
import com.example.recipes_helper.model.User;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.repository.ProductRepository;
import com.example.recipes_helper.repository.UserProductRepository;
import com.example.recipes_helper.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UserProductRepository userProductRepository;
    private final ProductRepository productRepository;

    @Override
    public List<UserProduct> findAllProductsByUser(Long userId) {
        return userProductRepository.findAllByUserId(userId);
    }


//    @Override
//    public UserProduct saveProduct(UserProduct product) {
//        Optional<UserProduct> existingUserProduct = userProductRepository.findByUserId(product.getUserId());
//        if (existingUserProduct.isPresent()) {
//            UserProduct existing = existingUserProduct.get();
//            existing.setCount(product.getCount());
//            return userProductRepository.save(existing);
//        }
//        return null;
//    }

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
//    @Transactional
//    public UserProduct saveProduct(UserProduct product) {
//        Product productEntity = productRepository.findById(product.getProductId())
//                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + product.getProductId()));
//        Optional<UserProduct> existingUserProduct = userProductRepository.findByUserIdAndProductId(
//                product.getUserId(),
//                product.getProductId()
//        );
//
//        if (existingUserProduct.isPresent()) {
//            // Обновляем количество
//            UserProduct existing = existingUserProduct.get();
//            existing.setCount(existing.getCount() + product.getCount());
//            return userProductRepository.save(existing);
//        } else {
//            // Создаем новую связь
//            UserProduct newUserProduct = new UserProduct();
//            newUserProduct.setUserId(user.getId());
//            newUserProduct.setProductId(productEntity.getId());
//            newUserProduct.setCount(product.getCount());
//            return userProductRepository.save(newUserProduct);
//        }
//    }
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
}