package com.example.recipes_helper.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.recipes_helper.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByUserId(Long userId);
}
