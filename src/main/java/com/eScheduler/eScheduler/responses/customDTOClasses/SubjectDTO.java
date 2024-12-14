package com.eScheduler.eScheduler.responses.customDTOClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;
    private String studyProgram;
    private Integer semester;
    private Integer lectureHours;
    private Integer exerciseHours;
    private Integer practicumHours;
    private String mandatory;
    private Integer lectureSessions;
    private Integer exerciseSessions;
}