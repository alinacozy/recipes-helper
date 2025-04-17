package com.example.recipes_helper.controller;

import com.example.recipes_helper.DTO.UserHistoryDTO;
import com.example.recipes_helper.DTO.UserHistoryRequest;
import com.example.recipes_helper.model.UserHistory;
import com.example.recipes_helper.services.HistoryService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/history")
@AllArgsConstructor
public class HistoryController {

    @Autowired
    private final HistoryService service;

    @GetMapping("/{userId}")
    public List<UserHistoryDTO> findHistoryByUser (@PathVariable Long userId){
        return service.findHistoryByUser(userId);
    }

    @PostMapping("/{userId}")
    public UserHistory saveRating (@PathVariable Long userId, @RequestBody UserHistoryRequest request){
        return service.saveRating(userId, request.getRecipeId(), request.getRating());
    }
    
}
