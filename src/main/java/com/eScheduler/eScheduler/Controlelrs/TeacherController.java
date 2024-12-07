package com.eScheduler.eScheduler.Controlelrs;

import com.eScheduler.eScheduler.model.Teacher;
import com.eScheduler.eScheduler.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Teacher> teachers() {
        return teacherService.getTeachers();
    }

    @PostMapping
    public void registerNewTeacher(@RequestBody Teacher teacher) {
        teacherService.addNewTeacher(teacher);
    }

    @DeleteMapping(path = {"{teacherId}"})
    public void deleteTeacher(@PathVariable("teacherId") Long id) {
        if(teacherService.getTeacher(id).isPresent()){
            teacherService.deleteTeacher(id);
        }
    }
    @PutMapping()
    public void updateTeacher(@RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher);
    }
}
