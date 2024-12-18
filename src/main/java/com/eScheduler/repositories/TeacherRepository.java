package com.eScheduler.repositories;

import com.eScheduler.model.Teacher;
import com.eScheduler.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t WHERE t.firstName = ?1")
    Optional<Teacher> findByName(String name);

    @Query("SELECT u FROM UserLogin u WHERE u.email = ?1")
    Optional<UserLogin> findByEmail(String email);

}
