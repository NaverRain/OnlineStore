package com.naverrain.persistence.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "purchase_status")
public class PurchaseStatusDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "status_name")
    private String statusName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseStatus() {
        return statusName;
    }

    public void setPurchaseStatus(String statusName) {
        this.statusName = statusName;
    }
}
