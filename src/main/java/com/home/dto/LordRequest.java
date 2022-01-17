package com.home.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LordRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull
    @Positive(message = "Age should be more than zero")
    private Integer age;

    public LordRequest() {
    }

    public LordRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
