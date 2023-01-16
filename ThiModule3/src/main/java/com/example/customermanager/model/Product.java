package com.example.customermanager.model;

import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private String price;
    private String amount;
    private String  color;
    private String description;

    private int iDcategory;

    public Product() {

    }

    public Product(long id, String name, String price, String amount, String color, String description, int iDcategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.color = color;
        this.description = description;
        this.iDcategory = iDcategory;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getiDcategory() {
        return iDcategory;
    }

    public void setiDcategory(int iDcategory) {
        this.iDcategory = iDcategory;
    }


}
