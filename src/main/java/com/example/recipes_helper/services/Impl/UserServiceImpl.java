package com.example.recipes_helper.services.Impl;

import com.example.recipes_helper.model.User;
import com.example.recipes_helper.model.UserProduct;
import com.example.recipes_helper.repository.UserRepository;
import com.example.recipes_helper.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) throws Exception {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new Exception("Пользователь с таким именем уже существует");
        }
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }


}
