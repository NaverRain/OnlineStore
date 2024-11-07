package com.naverrain.persistence.entities.impl;

import com.naverrain.persistence.entities.Product;

public class DefaultProduct implements Product {

    private int id;
    private String productName;
    private String categoryName;
    private double price;

    private String imgName;
    private String description;
    private String guid;

    public DefaultProduct(){

    }

    public DefaultProduct(int id, String productName, String categoryName, double price) {
        this.id = id;
        this.productName = productName;
        this.categoryName = categoryName;
        this.price = price;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getImgName() {
        return imgName;
    }

    @Override
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getGuid() {
        return this.guid;
    }

    @Override
    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", productName='" + productName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price;
    }
}
