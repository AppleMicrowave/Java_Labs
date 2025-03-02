package com.example.demo.model;



public class Pet {

    public enum Status {
        Available,
        Pending,
        Sold
    }

    public Pet() {
    }

    public Pet(int id, String name, String[] tags, Status status) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.status = status;
    }

    private int id;
    private String name;
    //private String category;
    private String[] tags;
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
