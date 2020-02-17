package com.training.alif.geeksfarm.marketplace.entity;

public class Category {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
