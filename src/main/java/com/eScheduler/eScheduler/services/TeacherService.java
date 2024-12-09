package com.eScheduler.eScheduler.services;

import com.eScheduler.eScheduler.exceptions.custom.ConflictException;
import com.eScheduler.eScheduler.exceptions.custom.NotFoundException;
import com.eScheduler.eScheduler.exceptions.custom.ServerErrorException;
import com.eScheduler.eScheduler.model.Subject;
import com.eScheduler.eScheduler.model.Teacher;
import com.eScheduler.eScheduler.repositories.TeacherRepository;
import com.eScheduler.eScheduler.responses.customDTOClasses.TeacherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDTO> getTeachers() {
      List<Teacher> teachers = teacherRepository.findAll();
      List<TeacherDTO> teacherDTOS = new ArrayList<>();
        teachers.forEach(teacher -> {
            teacherDTOS.add(mapToTeacherDTO(teacher));
        });
        return teacherDTOS;
    }

    public TeacherDTO addNewTeacher(Teacher teacher) {

         if (teacherRepository.findByName(teacher.getFirstName()).isPresent()) {
             throw new ConflictException("Profesor već postoji");
         }else{
             teacherRepository.save(teacher);
             return mapToTeacherDTO(teacher);
         }
    }

    public void deleteTeacherById(Long id){
        teacherRepository.findById(id).orElseThrow(()->new NotFoundException("Profesor nije pronađen"));
        teacherRepository.deleteById(id);
    }

    @Transactional
    public TeacherDTO updateTeacher(Teacher teacher) {
        Teacher oldTeacher = teacherRepository.findById(teacher.getId())
                .orElseThrow(()->new NotFoundException("Profesor nije pronađen"));

        for (Field field : Teacher.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object newValue = field.get(teacher);
                Object oldValue = field.get(oldTeacher);

                if (newValue != null && !newValue.equals(oldValue)) {
                    field.set(oldTeacher, newValue);
                }
            } catch (Exception e) {
                throw new ServerErrorException("Greska prilikom ažuriranja profesora");
            }
        }
        return mapToTeacherDTO(oldTeacher);

    }
    public TeacherDTO mapToTeacherDTO(Teacher teacher){
        return new TeacherDTO(teacher.getId(), teacher.getFirstName(), teacher.getLastName(), teacher.getTitle());
    }
}
