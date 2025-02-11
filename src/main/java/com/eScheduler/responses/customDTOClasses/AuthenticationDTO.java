package com.eScheduler.responses.customDTOClasses;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String message;
    private boolean success;
    private String token;

    public AuthenticationDTO(String message, boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token;
    }
}
