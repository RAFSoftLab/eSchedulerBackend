package com.eScheduler.eScheduler.Controlelrs;


import com.eScheduler.eScheduler.model.Subject;
import com.eScheduler.eScheduler.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Subject> getAllSubjects() {
        return subjectService.getSubjects();
    }

    @PostMapping
    public void createSubject(@RequestBody Subject subject){
        subjectService.addNewSubject(subject);
    }
    @DeleteMapping(path = {"{subjectId}"})
    public void deleteSubjectById(@PathVariable("subjectId") Long id){
        subjectService.deleteSubjectById(id);
    }

    @PutMapping(path = "{subjectId}")
    public void updateSubjectById(@PathVariable("subjectId") Long id,
                                  @RequestParam(required = true) String name){
        subjectService.updateSubjectById(id,name);
    }
}
