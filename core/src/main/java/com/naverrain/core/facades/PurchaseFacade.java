package com.naverrain.core.facades;

import com.naverrain.persistence.entities.Product;
import com.naverrain.persistence.entities.Purchase;
import com.naverrain.persistence.entities.User;

import java.util.List;

public interface PurchaseFacade {

    Integer LAST_STATUS_OF_ORDER_FULFILMENT_ID = 6;

    void createPurchase(User user, Product product);

    List<Purchase> getNotCompletedPurchases();

    void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId);
}
