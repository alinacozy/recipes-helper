package com.example.recipes_helper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.User;
import com.example.recipes_helper.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public String addUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        // для формы использовать @ModelAttribute
        try {
            String rawPassword = user.getPassword(); // сохраняем исходный пароль
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);

            // Автоматический вход через HttpServletRequest
            request.login(user.getUserName(), rawPassword);

            return "redirect:/recipes";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/signup";
        }
    }

    @GetMapping("/new-user")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/settings")
    public String changePassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @AuthenticationPrincipal MyUserDetails userDetails,
            Model model) {
        String response = userService.changePassword(userDetails.getId(), oldPassword, newPassword);
        model.addAttribute("message", response); // передаем в HTML сообщение-статус изменения пароля
        return "settings";
    }

}
