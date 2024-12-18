package com.eScheduler.controllers;


import com.eScheduler.model.Subject;
import com.eScheduler.responses.customDTOClasses.SubjectDTO;
import com.eScheduler.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectService.getSubjects();
        return ResponseEntity.status(HttpStatus.OK).body(subjects);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody Subject subject){
        SubjectDTO savedSubject = subjectService.addNewSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubject);
    }

    @DeleteMapping(path = {"{subjectId}"})
    public ResponseEntity<Void> deleteSubjectById(@PathVariable("subjectId") Long id){
        subjectService.deleteSubjectById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping()
    public ResponseEntity<SubjectDTO> updateSubjectById(@RequestBody Subject subject){
        SubjectDTO updatedSubject = subjectService.updateSubject(subject);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSubject);
    }
}
