package com.home.dto;

public class PlanetResponse {
    private int id;
    private String name;
    private Integer lordId;

    public PlanetResponse() {}

    public PlanetResponse(int id, String name, Integer lordId) {
        this.id = id;
        this.name = name;
        this.lordId = lordId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLordId() {
        return lordId;
    }
}
