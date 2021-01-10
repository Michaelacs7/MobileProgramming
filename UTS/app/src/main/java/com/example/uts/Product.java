package com.example.uts;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int price;
    private int qty;
    private int img;

    public Product(String name, int price, int qty, int img) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.img = img;
    }

    protected Product(int img, String name, int price){
        this.name = name;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
