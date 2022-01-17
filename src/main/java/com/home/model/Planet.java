package com.home.model;

import javax.persistence.*;

@Entity
@Table(name = "planets")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "lord_id")
    Integer lordId;

    public Planet(String name, Integer lordId) {
        this.name = name;
        this.lordId = lordId;
    }

    public Planet() {
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

    public void setLordId(Integer lordId) {
        this.lordId = lordId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
