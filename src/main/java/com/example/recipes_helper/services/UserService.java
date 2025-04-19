package com.example.recipes_helper.services;

import com.example.recipes_helper.model.User;

import java.util.List;

public interface UserService {
    void addUser (User user) throws Exception;
    List<User> getAllUsers();
}
