package com.example.demo.model;



public class Pet {

    public enum Status {
        Available,
        Pending,
        Sold
    }

    private int id;
    private String name;
    private Category category;
    private Tag[] tags;
    private Status status;

    public Pet() {

    }

    public Pet(int id, String name, Tag[] tags, Category category, Status status) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.category = category;
        this.status = status;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
