package com.example.recipes_helper.controller;

import com.example.recipes_helper.DTO.UserProductRequest;
import com.example.recipes_helper.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home(){
        return "index.html";
    }
//    @GetMapping("/index")
//    public String home(){
//        return "some_beautiful.html";
//    }
    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup.html";
    }

    // @GetMapping("/settings")
    // public String showSettingsForm(Model model) {
    //     model.addAttribute("user", new User()); // todo
    //     return "settings.html";
    // }

    @GetMapping("/edit_products")
    public String showProductForm(Model model) {
        model.addAttribute("UserProductRequest", new UserProductRequest());
        return "edit_products.html";
    }

    @GetMapping("/recipe_success")
    public String showSuccesPage() {
        return "recipe_success.html";
    }

//    @GetMapping("/login")
//    public String showAutorizationForm(Model model) {
//        return "login";
//    }


}
