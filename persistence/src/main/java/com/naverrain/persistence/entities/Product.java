package com.naverrain.persistence.entities;

import java.io.Serializable;

public interface Product extends Serializable {

    int getId();

    String getProductName();

    String getCategoryName();

    double getPrice();

    String getImgName();

    String getDescription();

    String getGuid();

    void setId(int id);

    void setProductName(String productName);

    void setCategoryName(String categoryName);

    void setPrice(double price);

    void setImgName(String imgName);

    void setDescription(String description);

    void setGuid(String guid);
}
