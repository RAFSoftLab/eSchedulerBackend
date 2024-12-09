package com.eScheduler.eScheduler.services;

import com.eScheduler.eScheduler.exceptions.custom.ConflictException;
import com.eScheduler.eScheduler.exceptions.custom.NotFoundException;
import com.eScheduler.eScheduler.exceptions.custom.ServerErrorException;
import com.eScheduler.eScheduler.model.Subject;
import com.eScheduler.eScheduler.repositories.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Subject addNewSubject(Subject subject){
        if(subjectRepository.findById(subject.getId()).isEmpty() && subjectRepository.findByName(subject.getName()).isEmpty()){
            return subjectRepository.save(subject);
        }else{
            throw new ConflictException("Predmet sa tim imenom vec postoji");
        }
    }

    public void deleteSubjectById(Long id){
        subjectRepository.findById(id).orElseThrow(() -> new NotFoundException("Predmet nije pronadjen"));
        subjectRepository.deleteById(id);
    }

    @Transactional
    public Subject updateSubject(Subject subject) {
        Subject oldSubject = subjectRepository.findById(subject.getId())
                .orElseThrow(() -> new NotFoundException("Predmet nije pronadjen"));

        for (Field field : Subject.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object newValue = field.get(subject);
                Object oldValue = field.get(oldSubject);

                if (newValue != null && !newValue.equals(oldValue)) {
                    field.set(oldSubject, newValue);
                }
            } catch (Exception e) {
                throw new ServerErrorException("Greska prilikom azuriranja predmeta");
            }
        }
        return oldSubject;
    }
}
