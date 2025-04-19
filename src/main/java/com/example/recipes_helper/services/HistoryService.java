package com.example.recipes_helper.services;

import com.example.recipes_helper.DTO.UserHistoryDTO;
import com.example.recipes_helper.model.Rating;
import com.example.recipes_helper.model.UserHistory;
import java.util.List;


public interface HistoryService {
    List<UserHistoryDTO> findHistoryByUser(Long userId);
    UserHistory saveRating(Long userId, Long recipeId, Rating rating);
    UserHistory saveCookedRecipe(Long userId, Long recipeId);

    class UserService {
    }
}
