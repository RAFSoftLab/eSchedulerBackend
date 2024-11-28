package com.eScheduler.eScheduler.services;


import com.eScheduler.eScheduler.model.Distribution;
import com.eScheduler.eScheduler.repositories.DistributionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionService {
    private final DistributionRepository distributionRepository;

    @Autowired
    public DistributionService(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }

    public List<Distribution> getAllDistributions() {
        return distributionRepository.findAll();
    }


    public void addNewDistribution(Distribution distribution){
        if(distributionRepository.findById(distribution.getId()).isEmpty()){
            distributionRepository.save(distribution);
        }else{
            throw new IllegalArgumentException("Subject exist");
        }
    }

    public void deleteDistributionById(Long id){
        if(distributionRepository.findById(id).isPresent()){
            distributionRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Subject do not exist with that id");
        }
    }

    @Transactional
    public void updateDistributionById(Long id, String classType){
        Distribution distribution = distributionRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Distribution not found"));
        if(!classType.isEmpty()){
            distribution.setClassType(classType);
        }
    }
}
