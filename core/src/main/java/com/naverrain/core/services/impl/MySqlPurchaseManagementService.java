package com.naverrain.core.services.impl;

import com.naverrain.persistence.dao.PurchaseDao;
import com.naverrain.persistence.dto.PurchaseDto;
import com.naverrain.persistence.dto.converter.PurchaseDtoToPurchaseConverter;
import com.naverrain.persistence.entities.Purchase;
import com.naverrain.core.services.PurchaseManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySqlPurchaseManagementService implements PurchaseManagementService {

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    PurchaseDtoToPurchaseConverter purchaseConverter;

    @Override
    public void addPurchase(Purchase purchase) {
        purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
    }

    @Override
    public List<Purchase> getPurchasesByUserId(int userId) {
        List<PurchaseDto> purchaseDtos = purchaseDao.getPurchaseByUserId(userId);
        return purchaseConverter.convertPurchaseDtosToPurchases(purchaseDtos);
    }

    @Override
    public List<Purchase> getPurchases() {
        List<PurchaseDto> purchaseDtos = purchaseDao.getPurchases();
        return purchaseConverter.convertPurchaseDtosToPurchases(purchaseDtos);
    }
}
