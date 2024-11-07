package com.naverrain.persistence.entities.impl;

import com.naverrain.persistence.entities.PurchaseStatus;

public class DefaultPurchaseStatus implements PurchaseStatus {

    private Integer id;
    private String statusName;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setPurchaseStatus(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getPurchaseStatus() {
        return this.statusName;
    }
}
