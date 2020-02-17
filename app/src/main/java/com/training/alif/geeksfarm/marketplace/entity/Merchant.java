package com.training.alif.geeksfarm.marketplace.entity;

public class Merchant {
    int id;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    String name,slug;

    public Merchant(int id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }
}
