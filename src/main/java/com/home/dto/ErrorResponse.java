package com.home.dto;

public class ErrorResponse {
   private final String message;

    public ErrorResponse(String massage) {
        this.message = massage;
    }

    public String getMessage() {
        return message;
    }
}
