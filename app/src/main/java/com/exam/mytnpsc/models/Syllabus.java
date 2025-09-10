package com.exam.mytnpsc.models;

public class Syllabus {
    private int id;
    private String name;

    Syllabus() {}

    public Syllabus(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
