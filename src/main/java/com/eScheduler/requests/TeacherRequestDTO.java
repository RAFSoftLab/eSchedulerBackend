package com.eScheduler.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRequestDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String title;
    private boolean isAdmin;
}
