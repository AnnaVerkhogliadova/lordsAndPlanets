package com.home.service.exception;

public class PlanetNotFoundException extends RuntimeException {
    public PlanetNotFoundException() {
        super("Planet not found");
    }
}
