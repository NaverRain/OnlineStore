package com.naverrain.persistence.entities.impl;

import com.naverrain.persistence.entities.Purchase;
import com.naverrain.persistence.entities.Product;
import com.naverrain.persistence.entities.PurchaseStatus;
import com.naverrain.persistence.entities.User;

import java.util.List;
import java.util.ArrayList;

public class DefaultPurchase implements Purchase {

    private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;

    private String creditCardNumber;
    private List<Product> products;
    private User customer;
    private Integer id;
    private PurchaseStatus purchaseStatus;


    @Override
    public boolean isCreditCardNumberValid(String creditCardNumber) {
        return creditCardNumber.toCharArray().length == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER &&
                !creditCardNumber.contains(" ") && Long.parseLong(creditCardNumber) > 0;
    }

    @Override
    public void setCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber == null) return;
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    @Override
    public List<Product> getProducts() {
        ArrayList<Product> productList = new ArrayList<Product>(this.products);
        return productList;
    }

    @Override
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public User getCustomer() {
        return this.customer;
    }

    @Override
    public String toString() {
        return "Default Purchase: [credit card number: " + creditCardNumber
                    + ", products: " + products + ", customer: "
                    + customer + ", purchase status: " + purchaseStatus + "]";
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public PurchaseStatus getPurchaseStatus() {
        return this.purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    @Override
    public double getTotalPurchaseCost() {
        return products.stream().mapToDouble(product -> product.getPrice()).sum();
    }
}
