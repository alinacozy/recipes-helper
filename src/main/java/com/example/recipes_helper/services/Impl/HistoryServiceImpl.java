package com.example.recipes_helper.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipes_helper.model.Rating;
import com.example.recipes_helper.model.UserHistory;
import com.example.recipes_helper.repository.UserHistoryRepository;
import com.example.recipes_helper.services.HistoryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    
    @Autowired
    private final UserHistoryRepository userHistoryRepository;

    public List<UserHistory> findHistoryByUser(Long userId) {
        return userHistoryRepository.findAllByUserId(userId);
    }

    public UserHistory saveRating(Long userId, Long recipeId, Rating rating) {
        Optional<UserHistory> existingUserHistory = userHistoryRepository.findByUserIdAndRecipeId(userId, recipeId);
        if (!existingUserHistory.isPresent()) {
            return null; // нужно ли как-то обработать такое исключение? что делать?
        }
        UserHistory existing = existingUserHistory.get();
        existing.setRate(rating);
        return userHistoryRepository.save(existing);
    }


    public UserHistory saveCookedRecipe(Long userId, Long recipeId) {
        Optional<UserHistory> existingUserHistory = userHistoryRepository.findByUserIdAndRecipeId(userId, recipeId);
        if (existingUserHistory.isPresent()) { //если пользователь уже готовил этот рецепт, обновляется дата и всё
            UserHistory existing = existingUserHistory.get();
            existing.setDate(new Date());
            return userHistoryRepository.save(existing);
        }
        UserHistory newUserHistory = new UserHistory();
        newUserHistory.setRecipeId(recipeId);
        newUserHistory.setUserId(userId);
        newUserHistory.setDate(new Date());
        return userHistoryRepository.save(newUserHistory);
    }
}
