// Made by Nicolavickh Yohanes 1772016
package com.nico.entity;

public class item {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public com.nico.entity.category getCategory() {
        return category;
    }

    public void setCategory(category category) {
        this.category = category;
    }

    private  String name;
    private double price;
    private category category;
}
