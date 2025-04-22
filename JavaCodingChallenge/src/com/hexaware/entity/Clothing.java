package com.hexaware.entity;

public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing(int productId, String productName, String description, double price, int quantityInStock, String type, String size, String color) {
        super(productId, productName, description, price, quantityInStock, type);
        this.size = size;
        this.color = color;
    }

    public String getSize() { return size; }
    public String getColor() { return color; }
}