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
             throw new IllegalArgumentException("Teacher already exists" + teacher.getFirstName());
         }else{
             System.out.println("creating teacher");
             teacherRepository.save(teacher);
         }
    }

    public void deleteTeacher(Long id){
        teacherRepository.deleteById(id);
    }

    @Transactional
    public void updateTeacher(Teacher teacher) {
        Teacher oldTeacher = teacherRepository.findById(teacher.getId()).orElseThrow(()->new IllegalArgumentException("Teacher not found"));
        if(oldTeacher.getFirstName().equals(teacher.getFirstName()) || !teacher.getFirstName().isEmpty()){
            oldTeacher.setFirstName(teacher.getFirstName());
        }
        if(oldTeacher.getLastName().equals(teacher.getLastName()) || !teacher.getLastName().isEmpty()){
            oldTeacher.setLastName(teacher.getLastName());
        }
        if(oldTeacher.getTitle().equals(teacher.getTitle()) || !teacher.getTitle().isEmpty()){
            oldTeacher.setTitle(teacher.getTitle());
        }
    }
}
