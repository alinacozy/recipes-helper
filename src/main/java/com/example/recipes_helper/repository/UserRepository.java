package com.example.recipes_helper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.recipes_helper.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long userId);

    Optional<User> findByUserName(String userName);
}
