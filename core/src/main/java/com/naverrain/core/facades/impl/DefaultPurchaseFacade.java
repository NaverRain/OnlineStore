package com.naverrain.core.facades.impl;

import com.naverrain.core.configs.CoreCofigurations;
import com.naverrain.core.facades.PurchaseFacade;
import com.naverrain.core.facades.UserFacade;
import com.naverrain.persistence.dao.PurchaseDao;
import com.naverrain.persistence.dao.impl.JpaPurchaseDao;
import com.naverrain.persistence.dto.converter.PurchaseDtoToPurchaseConverter;
import com.naverrain.persistence.entities.Product;
import com.naverrain.persistence.entities.Purchase;
import com.naverrain.persistence.entities.PurchaseStatus;
import com.naverrain.persistence.entities.User;
import com.naverrain.persistence.entities.impl.DefaultPurchase;
import com.naverrain.persistence.entities.impl.DefaultPurchaseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultPurchaseFacade implements PurchaseFacade {
    private static DefaultPurchaseFacade instance;

    private static PurchaseDao purchaseDao = new JpaPurchaseDao();
    private static PurchaseDtoToPurchaseConverter converter = new PurchaseDtoToPurchaseConverter();
    private static UserFacade userFacade = DefaultUserFacade.getInstance();

    @Override
    public void createPurchase(User user, Product product) {
        Purchase purchase = new DefaultPurchase();
        purchase.setCustomer(user);
        purchase.setProducts(new ArrayList<>(Arrays.asList(product)));

        var purchaseStatus = new DefaultPurchaseStatus();
        purchaseStatus.setId(1);
        purchase.setPurchaseStatus(purchaseStatus);

        purchaseDao.savePurchase(converter.convertPurchaseToPurchaseDto(purchase));
    }

    @Override
    public List<Purchase> getNotCompletedPurchases() {
        return converter.convertPurchaseDtosToPurchases(purchaseDao.getNotCompletedPurchases(LAST_STATUS_OF_ORDER_FULFILMENT_ID));
    }

    @Override
    public void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId) {
        Purchase purchase = converter.convertPurchaseDtoToPurchase(purchaseDao.getPurchaseById(purchaseId));
        PurchaseStatus purchaseStatus = purchase.getPurchaseStatus();

        int newPurchaseStatusId = purchaseStatus.getId() + 1;
        purchaseStatus.setId(newPurchaseStatusId);
        purchase.setPurchaseStatus(purchaseStatus);

        purchaseDao.updatePurchase(converter.convertPurchaseToPurchaseDto(purchase));

        if (LAST_STATUS_OF_ORDER_FULFILMENT_ID.equals(newPurchaseStatusId) && purchase.getCustomer().getReferrerUser() != null){
            User referrerUser = purchase.getCustomer().getReferrerUser();
            double shareFromPurchases = purchase.getTotalPurchaseCost() * CoreCofigurations.REFERRER_REWARD_RATE;
            referrerUser.setMoney(referrerUser.getMoney() + shareFromPurchases);
            userFacade.updateUser(referrerUser);
        }
    }


    public static synchronized DefaultPurchaseFacade getInstance(){
        if (instance == null){
            instance = new DefaultPurchaseFacade();
        }
        return instance;
    }
}
