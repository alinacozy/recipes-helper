package com.example.recipes_helper.services;

import com.example.recipes_helper.model.User;
import com.example.recipes_helper.repository.UserRepository;
import com.example.recipes_helper.config.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUserName(userName);
        return user.map(MyUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException(userName + "not found"));
    }
}
