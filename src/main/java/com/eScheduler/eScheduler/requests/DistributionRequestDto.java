package com.eScheduler.eScheduler.requests;

import com.eScheduler.eScheduler.responses.customDTOClasses.SubjectDTO;
import com.eScheduler.eScheduler.responses.customDTOClasses.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistributionRequestDto {
    private Long id;
    private String teacher;
    private String subject;
    private String classType;
    private Integer sessionCount;
}
