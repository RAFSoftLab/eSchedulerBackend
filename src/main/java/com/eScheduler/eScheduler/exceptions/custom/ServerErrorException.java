package com.eScheduler.eScheduler.exceptions.custom;

public class ServerErrorException extends RuntimeException {
    public ServerErrorException(String message) {
        super(message);
    }
}
