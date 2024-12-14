package com.eScheduler.eScheduler.services;


import com.eScheduler.eScheduler.exceptions.custom.ConflictException;
import com.eScheduler.eScheduler.exceptions.custom.NotFoundException;
import com.eScheduler.eScheduler.exceptions.custom.ServerErrorException;
import com.eScheduler.eScheduler.model.Distribution;
import com.eScheduler.eScheduler.model.Subject;
import com.eScheduler.eScheduler.model.Teacher;
import com.eScheduler.eScheduler.repositories.DistributionRepository;
import com.eScheduler.eScheduler.requests.DistributionRequestDto;
import com.eScheduler.eScheduler.responses.customDTOClasses.DistributionDTO;
import com.eScheduler.eScheduler.responses.customDTOClasses.SubjectDTO;
import com.eScheduler.eScheduler.responses.customDTOClasses.TeacherDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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


    public DistributionDTO addNewDistribution(DistributionRequestDto distribution){
        Subject subject = distributionRepository.findBySubjectName(distribution.getSubject());
        List<Distribution> distributionsWithSameSubject = distributionRepository.findBySubject(subject,distribution.getClassType());
        String fullName = distribution.getTeacher();
        String firstName = fullName.split(" ")[0];
        Teacher teacher = distributionRepository.findByTeacherName(firstName);
        if (distributionsWithSameSubject.isEmpty() || subject == null || teacher == null) {
            throw new ConflictException("Raspodela sa tim predmetom ili nastavnikom ne postoji");
        }else{
            AtomicInteger sum = new AtomicInteger();
            distributionsWithSameSubject.forEach(tmp -> {
                sum.addAndGet(tmp.getSessionCount());
            });
            sum.addAndGet(distribution.getSessionCount());

            if(Objects.equals(distribution.getClassType(), "vezbe") && sum.get() > subject.getExerciseSessions() ||
                    Objects.equals(distribution.getClassType(), "predavanja") && sum.get() > subject.getLectureSessions()){
                throw new ConflictException("Prekoracen broj casova za ovaj tip nastave");
            }
        }
        Distribution newDistribution = new Distribution(0L,teacher,subject,distribution.getClassType(),distribution.getSessionCount());

        if(distributionRepository.findById(newDistribution.getId()).isEmpty()){
            distributionRepository.save(newDistribution);
            return mapToDistributionDTO(newDistribution);
        }else{
            throw new ConflictException("Raspodela postoji");
        }
    }

    public void deleteDistributionById(Long id){
        distributionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Raspodela nije pronadjena"));
        distributionRepository.deleteById(id);
    }

    @Transactional
    public DistributionDTO updateDistribution(DistributionRequestDto distribution){
        Distribution oldDistribution = distributionRepository.findById(distribution.getId())
                .orElseThrow(() -> new NotFoundException("Raspodela sa tim Id ne postoji"));

        Subject subject = distributionRepository.findBySubjectName(distribution.getSubject());
        List<Distribution> distributionsWithSameSubject = distributionRepository.findBySubject(subject,distribution.getClassType());
        String fullName = distribution.getTeacher();
        String firstName = fullName.split(" ")[0];
        Teacher teacher = distributionRepository.findByTeacherName(firstName);

        if (distributionsWithSameSubject.isEmpty() || subject == null || teacher == null) {
            throw new ConflictException("Raspodela sa tim predmetom ili nastavnikom ne postoji");
        }else{
            AtomicInteger sum = new AtomicInteger();
            distributionsWithSameSubject.forEach(tmp -> {
                if(!Objects.equals(tmp.getId(), distribution.getId())){
                    sum.addAndGet(tmp.getSessionCount());
                }
            });
            sum.addAndGet(distribution.getSessionCount());

            if(Objects.equals(distribution.getClassType(), "vezbe") && sum.get() > subject.getExerciseSessions() ||
               Objects.equals(distribution.getClassType(), "predavanja") && sum.get() > subject.getLectureSessions()){
                throw new ConflictException("Prekoracen broj casova za ovaj tip nastave");
            }
        }

        Distribution newDistribution = new Distribution(distribution.getId(),teacher,subject,distribution.getClassType(),distribution.getSessionCount());
        for (Field field : Distribution.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object newValue = field.get(newDistribution);
                Object oldValue = field.get(oldDistribution);

                if (newValue != null && !newValue.equals(oldValue)) {
                    field.set(oldDistribution, newValue);
                }
            } catch (Exception e) {
                throw new ServerErrorException("Greska prilikom ažuriranja raspodele");
            }
        }
        return mapToDistributionDTO(newDistribution);
    }

    public DistributionDTO mapToDistributionDTO(Distribution distribution){
        TeacherDTO teacherDTO = new TeacherDTO(distribution.getTeacher().getId(), distribution.getTeacher().getFirstName(), distribution.getTeacher().getLastName(), distribution.getTeacher().getTitle());
        SubjectDTO subjectDTO = new SubjectDTO(distribution.getSubject().getId(), distribution.getSubject().getName(), distribution.getSubject().getStudyProgram(), distribution.getSubject().getSemester(),distribution.getSubject().getLectureHours(), distribution.getSubject().getExerciseHours(),distribution.getSubject().getPracticumHours(), distribution.getSubject().getMandatory(),distribution.getSubject().getLectureSessions(),distribution.getSubject().getExerciseSessions());
        return new DistributionDTO(distribution.getId(),teacherDTO,subjectDTO, distribution.getClassType(),distribution.getSessionCount());
    }
}
