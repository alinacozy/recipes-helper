package com.example.recipes_helper.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.recipes_helper.model.IdUserRecipe;
import com.example.recipes_helper.model.UserHistory;

public interface UserHistoryRepository extends CrudRepository<UserHistory, IdUserRecipe>{
    Optional<UserHistory> findByUserIdAndRecipeId(Long userId, Long recipeId);
    List<UserHistory> findAllByUserId(Long userId);
    Optional<UserHistory> findByUserId(Long userId);
}
