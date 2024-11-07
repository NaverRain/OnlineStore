package com.naverrain.persistence.entities;

public interface PurchaseStatus {

    void setId(Integer id);

    void setPurchaseStatus(String statusName);

    Integer getId();

    String getPurchaseStatus();
}
