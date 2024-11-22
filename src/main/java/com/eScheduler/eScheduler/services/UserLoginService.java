package com.eScheduler.eScheduler.services;

import com.eScheduler.eScheduler.model.UserLogin;
import com.eScheduler.eScheduler.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private final UserLoginRepository userLoginRepository;

    @Autowired
    public UserLoginService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    public UserLogin getUserByCredentials(String email,String password){
        return userLoginRepository.findByCredentials(email,password).orElseThrow(()-> new IllegalArgumentException("Password or Email is incorrect"));

    }
}
