package com.example.recipes_helper.controller;

import com.example.recipes_helper.DTO.UserHistoryDTO;
import com.example.recipes_helper.DTO.UserHistoryRequest;
import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.Rating;
import com.example.recipes_helper.services.HistoryService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/history")
@AllArgsConstructor
public class HistoryController {

    @Autowired
    private final HistoryService service;

    @PostMapping()
    public String saveRating (@AuthenticationPrincipal MyUserDetails userDetails, @ModelAttribute UserHistoryRequest request){
        service.saveRating(userDetails.getId(), request.getRecipeId(), request.getRating());
        return "redirect:/history"; // Перенаправление обратно на страницу истории
    }

    @GetMapping()
    public String findHistoryByUser (@AuthenticationPrincipal MyUserDetails userDetails, Model model){
        List<UserHistoryDTO> history= service.findHistoryByUser(userDetails.getId());
        model.addAttribute("history", history);
        model.addAttribute("ratings", Rating.values()); //передаем в HTML наши значения enum Rating
        return "history";
    }


}
