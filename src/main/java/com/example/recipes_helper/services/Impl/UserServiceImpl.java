package com.example.recipes_helper.services.Impl;

import com.example.recipes_helper.model.User;
import com.example.recipes_helper.repository.UserRepository;
import com.example.recipes_helper.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String changePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findByUserId(id);
        if (user == null){
            return "ERROR: пользователь не нашелся(";
        }

        // Проверяем старый пароль
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "ERROR: Неверный старый пароль!!!";
        }

        // Обновляем пароль
        user.setPassword(passwordEncoder.encode(newPassword));
        // сохраняем измененного пользователя в базе
        userRepository.save(user);

        return "Пароль изменен успешно";
    }

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
