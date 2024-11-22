package com.eScheduler.eScheduler.repositories;

import com.eScheduler.eScheduler.model.Teacher;
import com.eScheduler.eScheduler.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {


    @Query("SELECT ul FROM UserLogin ul WHERE ul.email = ?1 AND ul.password = ?2")
    Optional<UserLogin> findByCredentials(String email,String password);
}
