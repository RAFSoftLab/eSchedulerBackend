package com.eScheduler.eScheduler.services;


import com.eScheduler.eScheduler.exceptions.custom.ConflictException;
import com.eScheduler.eScheduler.exceptions.custom.NotFoundException;
import com.eScheduler.eScheduler.exceptions.custom.ServerErrorException;
import com.eScheduler.eScheduler.model.Distribution;
import com.eScheduler.eScheduler.model.Subject;
import com.eScheduler.eScheduler.model.Teacher;
import com.eScheduler.eScheduler.repositories.DistributionRepository;
import com.eScheduler.eScheduler.responses.customDTOClasses.DistributionDTO;
import com.eScheduler.eScheduler.responses.customDTOClasses.SubjectDTO;
import com.eScheduler.eScheduler.responses.customDTOClasses.TeacherDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionService {
    private final DistributionRepository distributionRepository;

    @Autowired
    public DistributionService(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }

    public List<DistributionDTO> getAllDistributions() {
         List<Distribution> distributions = distributionRepository.findAll();
         List<DistributionDTO> distributionDTOS = new ArrayList<>();
        distributions.forEach(distribution -> {
            distributionDTOS.add(mapToDistributionDTO(distribution));
        });
        return distributionDTOS;
    }


    public DistributionDTO addNewDistribution(Distribution distribution){
        if(distributionRepository.findById(distribution.getId()).isEmpty()){
            distributionRepository.save(distribution);
            return mapToDistributionDTO(distribution);
        }else{
            throw new ConflictException("Raspodela postoji");
        }
    }

    public void deleteDistributionById(Long id){
        distributionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Raspodela nije pronadjena"));
    }

    @Transactional
    public DistributionDTO updateDistribution(Distribution distribution){
        Distribution oldDistribution = distributionRepository.findById(distribution.getId())
                .orElseThrow(() -> new NotFoundException("Raspodela sa tim Id ne postoji"));

        for (Field field : Distribution.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object newValue = field.get(distribution);
                Object oldValue = field.get(oldDistribution);

                if (newValue != null && !newValue.equals(oldValue)) {
                    field.set(oldDistribution, newValue);
                }
            } catch (Exception e) {
                throw new ServerErrorException("Greska prilikom ažuriranja raspodele");
            }
        }
        return mapToDistributionDTO(oldDistribution);
    }

    public DistributionDTO mapToDistributionDTO(Distribution distribution){
        TeacherDTO teacherDTO = new TeacherDTO(distribution.getTeacher().getId(), distribution.getTeacher().getFirstName(), distribution.getTeacher().getLastName(), distribution.getTeacher().getTitle());
        SubjectDTO subjectDTO = new SubjectDTO(distribution.getSubject().getId(), distribution.getSubject().getName(), distribution.getSubject().getStudyProgram(), distribution.getSubject().getSemester(),distribution.getSubject().getLectureHours(), distribution.getSubject().getExerciseHours(),distribution.getSubject().getPracticumHours(), distribution.getSubject().getMandatory(),distribution.getSubject().getLectureSessions(),distribution.getSubject().getExerciseSessions());
        return new DistributionDTO(distribution.getId(),teacherDTO,subjectDTO, distribution.getClassType(),distribution.getSessionCount());
    }
}
