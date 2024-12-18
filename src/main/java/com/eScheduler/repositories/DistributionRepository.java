package com.eScheduler.repositories;

import com.eScheduler.model.Distribution;
import com.eScheduler.model.Subject;
import com.eScheduler.model.Teacher;
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

    @Query("SELECT t FROM Teacher t WHERE t.firstName = ?1 ")
    Teacher findByTeacherName(String name);
}
