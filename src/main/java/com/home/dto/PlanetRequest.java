package com.home.dto;

import javax.validation.constraints.NotBlank;

public class PlanetRequest {
    @NotBlank(message = "Name is required")
    private String name;

    public PlanetRequest(){}

    public PlanetRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
