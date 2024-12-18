package com.eScheduler.services;

import com.eScheduler.model.UserLogin;
import com.eScheduler.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserLoginService implements UserDetailsService {
    private final UserLoginRepository userLoginRepository;

    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    public UserLogin getUserByCredentials(String email,String password){
        return userLoginRepository.findByCredentials(email,password).orElseThrow(()-> new IllegalArgumentException("Password or Email is incorrect"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin user = userLoginRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
