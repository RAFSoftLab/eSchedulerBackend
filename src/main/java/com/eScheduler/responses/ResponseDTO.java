package com.eScheduler.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO<T> {
    private String message;
    private boolean success;
    private T data;

    public ResponseDTO(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

}
