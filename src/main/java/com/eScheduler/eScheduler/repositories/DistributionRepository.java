package com.eScheduler.eScheduler.repositories;

import com.eScheduler.eScheduler.model.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {

}
