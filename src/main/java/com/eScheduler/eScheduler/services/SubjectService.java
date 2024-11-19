package com.eScheduler.eScheduler.services;

import com.eScheduler.eScheduler.model.Subject;
import com.eScheduler.eScheduler.repositories.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void addNewSubject(Subject subject){
        if(subjectRepository.findById(subject.getId()).isEmpty()){
            subjectRepository.save(subject);
        }else{
            throw new IllegalArgumentException("Subject exist");
        }
    }

    public void deleteSubjectById(Long id){
        if(subjectRepository.findById(id).isPresent()){
            subjectRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException("Subject do not exist with that id");
        }
    }

    @Transactional
    public void updateSubjectById(Long id, String name){
        Subject subject = subjectRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Subject not found"));
        if(!name.isEmpty()){
            subject.setName(name);
        }
    }

}
