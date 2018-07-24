package com.bsonin.sparkblog.model;

public class Tag {
    //TODO:bhs - Leave as wrapper for now - Maybe just eliminate and use String
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // TODO:bhs - Equals and HashCode
}
