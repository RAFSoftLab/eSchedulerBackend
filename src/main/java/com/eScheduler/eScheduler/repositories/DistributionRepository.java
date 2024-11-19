package com.eScheduler.eScheduler.repositories;

import com.eScheduler.eScheduler.model.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {
}
