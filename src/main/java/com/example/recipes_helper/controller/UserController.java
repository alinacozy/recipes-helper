package com.example.recipes_helper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.recipes_helper.config.MyUserDetails;
import com.example.recipes_helper.model.User;
import com.example.recipes_helper.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<String> addUser(@ModelAttribute User user) {
        // для формы использовать @ModelAttribute
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.addUser(user);
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
