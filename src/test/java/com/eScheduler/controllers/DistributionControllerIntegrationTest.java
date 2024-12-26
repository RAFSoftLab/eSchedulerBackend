package com.eScheduler.controllers;

import com.eScheduler.model.Subject;
import com.eScheduler.repositories.DistributionRepository;
import com.eScheduler.requests.DistributionRequestDTO;
import com.eScheduler.requests.TeacherRequestDTO;
import com.eScheduler.responses.customDTOClasses.DistributionDTO;
import com.eScheduler.services.DistributionService;
import com.eScheduler.services.SubjectService;
import com.eScheduler.services.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DistributionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DistributionService distributionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DistributionRepository distributionRepository;

    @BeforeEach
    void setUp() {
        subjectService.addNewSubject(new Subject(null, "Matematika", "RN", 1, 3, 2, 1, "obavezan", 15, 10, null));
        subjectService.addNewSubject(new Subject(null, "Programiranje", "RN", 2, 4, 2, 0, "izbroni", 16, 8, null));

        teacherService.addNewTeacher(new TeacherRequestDTO(null,"pPetrovic@raf.rs", "Petar", "Petrovic", "nastavnik",false));
        teacherService.addNewTeacher(new TeacherRequestDTO(null,"aAnic@raf.rs", "Ana", "Anic", "saradnik",true));

        distributionService.addNewDistribution(new DistributionRequestDTO(null, "pPetrovic@raf.rs", "Matematika", "predavanja", 10));
        distributionService.addNewDistribution(new DistributionRequestDTO(null, "aAnic@raf.rs", "Programiranje", "vezbe", 5));
    }

    @Test
    void getAllDistributions_returnsDistributionsList() throws Exception {
        mockMvc.perform(get("/api/distributions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].subject.name", is("Matematika")))
                .andExpect(jsonPath("$[1].subject.name", is("Programiranje")));
    }

    @Test
    void createDistribution_createsNewDistribution() throws Exception {
        String newDistributionJson = """
            {
                "teacher": "pPetrovic@raf.rs",
                "subject": "Matematika",
                "classType": "vezbe",
                "sessionCount": 3
            }
            """;

        mockMvc.perform(post("/api/distributions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newDistributionJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject.name", is("Matematika")))
                .andExpect(jsonPath("$.classType", is("vezbe")))
                .andExpect(jsonPath("$.sessionCount", is(3)));
    }

    @Test
    void deleteDistributionById_deletesDistribution() throws Exception {
        DistributionDTO distribution = distributionService.getAllDistributions().stream()
                .filter(d -> d.getSubject().getName().equals("Programiranje"))
                .findFirst()
                .orElseThrow();

        mockMvc.perform(delete("/api/distributions/" + distribution.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/distributions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void updateDistribution_updatesDistribution() throws Exception {
        DistributionDTO distribution = distributionService.getAllDistributions().stream()
                .filter(d -> d.getSubject().getName().equals("Matematika"))
                .findFirst()
                .orElseThrow();

        String updatedDistributionJson = String.format("""
            {
                "id": %d,
                "teacher": "pPetrovic@raf.rs",
                "subject": "Matematika",
                "classType": "vezbe",
                "sessionCount": 3
            }
            """, distribution.getId());

        mockMvc.perform(put("/api/distributions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDistributionJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(distribution.getId().intValue())))
                .andExpect(jsonPath("$.subject.name", is("Matematika")))
                .andExpect(jsonPath("$.classType", is("vezbe")))
                .andExpect(jsonPath("$.sessionCount", is(3)));
        
    }

}
