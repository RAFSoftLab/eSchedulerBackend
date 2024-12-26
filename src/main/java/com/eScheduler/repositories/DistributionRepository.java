package com.eScheduler.repositories;

import com.eScheduler.model.Distribution;
import com.eScheduler.model.Subject;
import com.eScheduler.model.Teacher;
import com.eScheduler.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {

    @Query("SELECT d FROM Distribution d WHERE d.subject = ?1 AND d.classType = ?2")
    List<Distribution> findBySubject(Subject subject, String classType);

    @Query("SELECT s FROM Subject s WHERE s.name = ?1 ")
    Subject findBySubjectName(String name);

    @Query("SELECT u FROM UserLogin u WHERE u.email = ?1 ")
    UserLogin findByUserEmail(String email);


    @Query("SELECT t FROM Teacher t WHERE t.userLogin.email = ?1 ")
    Teacher findByTeacherEmail(String email);
}

