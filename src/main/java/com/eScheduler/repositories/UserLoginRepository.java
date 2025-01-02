package com.eScheduler.repositories;

import com.eScheduler.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {


    @Query("SELECT ul FROM UserLogin ul WHERE ul.email = ?1 AND ul.password = ?2")
    Optional<UserLogin> findByCredentials(String email,String password);

    @Query("SELECT ul FROM UserLogin ul WHERE ul.email = ?1")
    Optional<UserLogin> findByEmail(String email);
}
