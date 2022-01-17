package com.home.service.exception;

public class LordIdNotFoundException extends RuntimeException {
    public LordIdNotFoundException() {
        super("Lord ID not found");
    }
}
