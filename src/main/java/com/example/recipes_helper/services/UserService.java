package com.example.recipes_helper.services;

import com.example.recipes_helper.model.User;

import java.util.List;

public interface UserService {
    String changePassword(Long id, String oldPassword, String newPassword);
    void addUser (User user) throws Exception;
    List<User> getAllUsers();
}
