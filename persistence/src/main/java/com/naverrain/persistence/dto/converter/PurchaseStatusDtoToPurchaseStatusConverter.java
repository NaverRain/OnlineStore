package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.PurchaseStatusDto;
import com.naverrain.persistence.entities.PurchaseStatus;
import com.naverrain.persistence.entities.impl.DefaultPurchaseStatus;
import org.springframework.stereotype.Service;

@Service
public class PurchaseStatusDtoToPurchaseStatusConverter {

    public PurchaseStatus convertPurchaseStatusDtoToPurchaseStatus(PurchaseStatusDto purchaseStatusDto){
        PurchaseStatus purchaseStatus = new DefaultPurchaseStatus();
        purchaseStatus.setId(purchaseStatusDto.getId());
        purchaseStatus.setPurchaseStatus(purchaseStatusDto.getPurchaseStatus());
        return purchaseStatus;
    }

    public PurchaseStatusDto convertPurchaseStatusToPurchaseStatusDto(PurchaseStatus purchaseStatus){
        PurchaseStatusDto purchaseStatusDto = new PurchaseStatusDto();
        purchaseStatusDto.setId(purchaseStatus.getId());
        purchaseStatusDto.setPurchaseStatus(purchaseStatus.getPurchaseStatus());
        return purchaseStatusDto;
    }
}
