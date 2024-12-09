package com.eScheduler.eScheduler.controllers;

import com.eScheduler.eScheduler.model.Teacher;
import com.eScheduler.eScheduler.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> teachers() {
        List<Teacher> teachers = teacherService.getTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    @PostMapping
    public ResponseEntity<Teacher> registerNewTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherService.addNewTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    @DeleteMapping(path = {"{teacherId}"})
    public ResponseEntity<Void> deleteTeacher(@PathVariable("teacherId") Long id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping()
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher){
        Teacher updatedTeacher = teacherService.updateTeacher(teacher);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacher);
    }
}
