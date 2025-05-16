package com.example.recipes_helper.services.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipes_helper.DTO.UserHistoryDTO;
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

    public List<UserHistoryDTO> findHistoryByUser(Long userId) {
        List<UserHistory> userHistories = userHistoryRepository.findAllByUserId(userId);
        List<UserHistoryDTO> result = new ArrayList<>();
        for (UserHistory uh : userHistories){
            result.add(new UserHistoryDTO(uh.getRecipeId(), uh.getRecipe().getRecipeName(), uh.getDate(), uh.getRate()));
        }
        return result;
    }

    public UserHistoryDTO findHistoryByUserAndRecipe(Long userId, Long recipeId){
        UserHistory uh = userHistoryRepository.findByUserIdAndRecipeId(userId, recipeId)
            .orElseThrow(() -> new RuntimeException("Recipe history not found")); // ищем историю данного рецепта по id юзера и рецепта
        UserHistoryDTO result = new UserHistoryDTO(uh.getRecipeId(), uh.getRecipe().getRecipeName(), uh.getDate(), uh.getRate());
        return result;
    }

    public UserHistory saveRating(Long userId, Long recipeId, Rating rating) {
        Optional<UserHistory> existingUserHistory = userHistoryRepository.findByUserIdAndRecipeId(userId, recipeId);
        if (!existingUserHistory.isPresent()) {
            return null; // нужно ли как-то обработать такое исключение? что делать?
        }
        UserHistory existing = existingUserHistory.get();
        if (rating == Rating.NONE) {
            existing.setRate(null); // отсутствие оценки
        } else {
            existing.setRate(rating);
        }
        return userHistoryRepository.save(existing);
    }


    public UserHistory saveCookedRecipe(Long userId, Long recipeId) {
        Optional<UserHistory> existingUserHistory = userHistoryRepository.findByUserIdAndRecipeId(userId, recipeId);
        LocalDate today = LocalDate.now();

        if (existingUserHistory.isPresent()) { //если пользователь уже готовил этот рецепт, обновляется дата и всё
            UserHistory existing = existingUserHistory.get();
            existing.setDate(today);
            UserHistory savedHistory = userHistoryRepository.save(existing);
            return savedHistory;
        }
        UserHistory newUserHistory = new UserHistory();
        newUserHistory.setRecipeId(recipeId);
        newUserHistory.setUserId(userId);
        newUserHistory.setDate(today);
        return userHistoryRepository.save(newUserHistory);
    }
}
