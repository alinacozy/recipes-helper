package com.example.recipes_helper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.recipes_helper.model.IdUserProduct;
import com.example.recipes_helper.model.UserProduct;

public interface UserProductRepository extends CrudRepository<UserProduct, IdUserProduct>{
  Optional<UserProduct> findByUserIdAndProductId(Long userId, Long productId);
  List<UserProduct> findAllByUserId(Long userId);
  Optional<UserProduct> findByUserId(Long userId);
}