package com.eScheduler.eScheduler.Controlelrs;

import com.eScheduler.eScheduler.services.DistributionService;
import com.eScheduler.eScheduler.model.Distribution;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Distribution> getDistributions() {
        return distributionService.getAllDistributions();
    }

    @PostMapping
    public void createDistribution(@RequestBody Distribution distribution){
        distributionService.addNewDistribution(distribution);
    }

    @DeleteMapping(path = "{distributionId}")
    public void deleteDitributionById(@PathVariable("distributionId")Long id){
        distributionService.deleteDistributionById(id);
    }

    @PutMapping(path = "{distributionId}")
    public void updateDistributionById(@PathVariable("distributionId") Long id,
                                        @RequestParam String classType){
        updateDistributionById(id,classType);
    }
}
