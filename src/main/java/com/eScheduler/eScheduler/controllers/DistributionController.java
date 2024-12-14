package com.eScheduler.eScheduler.controllers;

import com.eScheduler.eScheduler.requests.DistributionRequestDto;
import com.eScheduler.eScheduler.responses.customDTOClasses.DistributionDTO;
import com.eScheduler.eScheduler.services.DistributionService;
import com.eScheduler.eScheduler.model.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/distributions")
public class DistributionController {
    private final DistributionService distributionService;

    @Autowired
    public DistributionController(DistributionService distributionService) {
        this.distributionService = distributionService;
    }

    @GetMapping
    public ResponseEntity<List<DistributionDTO>> getDistributions() {
        List<DistributionDTO> distributions = distributionService.getAllDistributions();
        return ResponseEntity.status(HttpStatus.OK).body(distributions);
    }

    @PostMapping
    public ResponseEntity<DistributionDTO> createDistribution(@RequestBody DistributionRequestDto distribution){
        DistributionDTO savedDistribution = distributionService.addNewDistribution(distribution);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDistribution);
    }

    @DeleteMapping(path = "{distributionId}")
    public ResponseEntity<Void> deleteDistributionById(@PathVariable("distributionId")Long id){
        distributionService.deleteDistributionById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping()
    public ResponseEntity<DistributionDTO> updateDistribution(@RequestBody DistributionRequestDto distribution){
        DistributionDTO distributionDTO =distributionService.updateDistribution(distribution);
        return ResponseEntity.status(HttpStatus.OK).body(distributionDTO);
    }
}
