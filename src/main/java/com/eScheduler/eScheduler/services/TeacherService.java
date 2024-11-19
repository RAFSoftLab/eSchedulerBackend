package com.eScheduler.eScheduler.services;

import com.eScheduler.eScheduler.model.Teacher;
import com.eScheduler.eScheduler.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getTeachers() {
      return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacher(Long id) {
            Optional<Teacher> teacherById = teacherRepository.findById(id);
            if (!teacherById.isPresent()) {
                throw new IllegalArgumentException("Teacher not found");
            }
            else return teacherById;
    }

    public void addNewTeacher(Teacher teacher) {
         Optional<Teacher> teacherByName = teacherRepository.findByName(teacher.getFirstName());
         if (teacherByName.isPresent()) {
             throw new IllegalArgumentException("Teacher already exists");
         }else{
             System.out.println("creating teacher");
             teacherRepository.save(teacher);
         }
    }

    public void deleteTeacher(Long id){
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Long id,String firstName) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Teacher not found"));
        System.out.println(teacher.getFirstName());
        System.out.println(firstName);
        if (firstName != null && !firstName.isEmpty()){
            teacher.setFirstName(firstName);
        }
    }
}
