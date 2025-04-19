package com.example.recipes_helper.controller;

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

//    @GetMapping("/login")
//    public String showAutorizationForm(Model model) {
//        return "login";
//    }


}
