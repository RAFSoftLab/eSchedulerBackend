package com.eScheduler.services;

import com.eScheduler.TestDataProvider;
import com.eScheduler.exceptions.custom.ConflictException;
import com.eScheduler.exceptions.custom.NotFoundException;
import com.eScheduler.model.Teacher;
import com.eScheduler.model.UserLogin;
import com.eScheduler.repositories.TeacherRepository;
import com.eScheduler.repositories.UserLoginRepository;
import com.eScheduler.requests.TeacherRequestDTO;
import com.eScheduler.responses.customDTOClasses.TeacherDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserLoginRepository userLoginRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTeachers_returnsListOfTeacherDTOs() {
        Teacher teacher1 = TestDataProvider.createTeacher1();
        Teacher teacher2 = TestDataProvider.createTeacher2();
        List<Teacher> teachers = List.of(teacher1, teacher2);

        when(teacherRepository.findAll()).thenReturn(teachers);

        List<TeacherDTO> result = teacherService.getTeachers();

        assertEquals(2, result.size());
        assertEquals("Marko", result.get(0).getFirstName());
    }

    @Test
    void addNewTeacher_savesAndReturnsTeacherDTO() {
        TeacherRequestDTO request = new TeacherRequestDTO(1L, "mMarkovic@example.com", "Marko", "Markovic", "Profesor", true);
        Teacher newTeacher = TestDataProvider.createTeacher1();

        when(teacherRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString())).thenReturn("password1");
        when(userLoginRepository.save(any(UserLogin.class))).thenReturn(newTeacher.getUserLogin());
        when(teacherRepository.save(any(Teacher.class))).thenReturn(newTeacher);

        TeacherDTO result = teacherService.addNewTeacher(request);

        assertEquals("Marko", result.getFirstName());
    }

    @Test
    void addNewTeacher_throwsConflictException_whenTeacherExists() {
        TeacherRequestDTO request = new TeacherRequestDTO(1L, "mMarkovic@example.com", "Marko", "Markovic", "Profesor", true);

        Teacher teacher = TestDataProvider.createTeacher1();
        when(teacherRepository.findByEmail(request.getEmail())).thenReturn(Optional.ofNullable(teacher.getUserLogin()));

        assertThrows(ConflictException.class, () -> teacherService.addNewTeacher(request));
    }

    @Test
    void deleteTeacherById_deletesTeacher() {
        Long teacherId = 1L;
        Teacher teacher = TestDataProvider.createTeacher1();
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        teacherService.deleteTeacherById(teacherId);

        verify(userLoginRepository, times(1)).deleteById(teacher.getUserLogin().getId());
    }

    @Test
    void deleteTeacherById_throwsNotFoundException_whenTeacherNotFound() {
        Long teacherId = 1L;
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> teacherService.deleteTeacherById(teacherId));
    }

    @Test
    void updateTeacher_updatesAndReturnsTeacherDTO() {
        TeacherRequestDTO request = new TeacherRequestDTO(1L, "mMarkovic@example.com", "Marko", "Markovic", "Profesor", true);
        Teacher oldTeacher = TestDataProvider.createTeacher1();
        when(teacherRepository.findById(request.getId())).thenReturn(Optional.of(oldTeacher));

        TeacherDTO result = teacherService.updateTeacher(request);

        assertEquals("Marko", result.getFirstName());
    }

    @Test
    void updateTeacher_throwsNotFoundException_whenTeacherNotFound() {
        TeacherRequestDTO request = new TeacherRequestDTO(1L, "mMarkovic@example.com", "Marko", "Markovic", "Profesor", true);
        when(teacherRepository.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> teacherService.updateTeacher(request));
    }
}
