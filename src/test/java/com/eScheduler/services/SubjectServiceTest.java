package com.eScheduler.services;

import com.eScheduler.exceptions.custom.ConflictException;
import com.eScheduler.exceptions.custom.NotFoundException;
import com.eScheduler.model.Subject;
import com.eScheduler.repositories.SubjectRepository;
import com.eScheduler.responses.customDTOClasses.SubjectDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSubjects_returnsListOfSubjectDTOs() {
        // Arrange
        List<Subject> subjects = List.of(
                new Subject(1L,"OOP", "RN",2,2,2,2,"obavezni",2,2,null),
                new Subject(1L,"NMA", "RN",2,2,2,2,"izborni",2,2,null));
        when(subjectRepository.findAll()).thenReturn(subjects);

        // Act
        List<SubjectDTO> result = subjectService.getSubjects();

        // Assert
        assertEquals(2, result.size());
        assertEquals("OOP", result.get(0).getName());
        assertEquals("NMA", result.get(1).getName());
    }

    @Test
    void addNewSubject_savesAndReturnsSubjectDTO() {
        // Arrange
        Subject subject = new Subject(1L,"OOP", "RN",2,2,2,2,"obavezni",2,2,null);
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());
        when(subjectRepository.findByName(subject.getName())).thenReturn(Optional.empty());
        when(subjectRepository.save(subject)).thenReturn(subject);

        // Act
        SubjectDTO result = subjectService.addNewSubject(subject);

        // Assert
        assertEquals("OOP", result.getName());
    }

    @Test
    void addNewSubject_throwsConflictException_whenSubjectExists() {
        // Arrange
        Subject subject = new Subject(1L,"OOP", "RN",2,2,2,2,"obavezni",2,2,null);
        when(subjectRepository.findByName(subject.getName())).thenReturn(Optional.of(subject));

        // Act & Assert
        assertThrows(ConflictException.class, () -> subjectService.addNewSubject(subject));
    }

    @Test
    void deleteSubjectById_deletesSubject() {
        // Arrange
        Long subjectId = 1L;
        Subject subject = new Subject(subjectId,"OOP", "RN",2,2,2,2,"obavezni",2,2,null);
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        // Act
        subjectService.deleteSubjectById(subjectId);

        // Assert
        verify(subjectRepository, times(1)).deleteById(subjectId);
    }

    @Test
    void deleteSubjectById_throwsNotFoundException_whenSubjectNotFound() {
        // Arrange
        Long subjectId = 1L;
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> subjectService.deleteSubjectById(subjectId));
    }

    @Test
    void updateSubject_updatesAndReturnsSubjectDTO() {
        // Arrange
        Subject subject = new Subject(1L,"OOP", "RN",2,2,2,2,"obavezni",2,2,null);
        Subject updatedSubject = new Subject(1L,"NMA", "RN",2,2,2,2,"obavezni",2,2,null);
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));

        // Act
        SubjectDTO result = subjectService.updateSubject(updatedSubject);

        // Assert
        assertEquals("NMA", result.getName());
    }

    @Test
    void updateSubject_throwsNotFoundException_whenSubjectNotFound() {
        // Arrange
        Subject subject = new Subject(1L,"OOP", "RN",2,2,2,2,"obavezni",2,2,null);
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> subjectService.updateSubject(subject));
    }
}
