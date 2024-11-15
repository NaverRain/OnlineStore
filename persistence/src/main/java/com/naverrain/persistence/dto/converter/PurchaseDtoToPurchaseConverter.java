package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.PurchaseDto;
import com.naverrain.persistence.entities.Purchase;
import com.naverrain.persistence.entities.impl.DefaultPurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseDtoToPurchaseConverter {

    @Autowired
    private ProductDtoToProductConverter productConverter;

    @Autowired
    private UserDtoToUserConverter userConverter;

    @Autowired
    private PurchaseStatusDtoToPurchaseStatusConverter purchaseStatusConverter;


    public Purchase convertPurchaseDtoToPurchase(PurchaseDto purchaseDto) {
        Purchase purchase = new DefaultPurchase();
        purchase.setId(purchaseDto.getId());
        if (purchaseDto.getUserDto() != null){
            purchase.setCreditCardNumber(purchaseDto.getUserDto().getCreditCard());
        }
        purchase.setCustomer(userConverter.convertUserDtoToUser(purchaseDto.getUserDto()));
        purchase.setProducts(productConverter.convertProductDtosToProducts(purchaseDto.getProductDtos()));
        purchase.setPurchaseStatus(purchaseStatusConverter.convertPurchaseStatusDtoToPurchaseStatus(purchaseDto.getPurchaseStatusDto()));

        return purchase;
    }

    public PurchaseDto convertPurchaseToPurchaseDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        if (purchase.getId() != null){
            purchaseDto.setId(purchase.getId());
        }
        purchaseDto.setProductDtos(productConverter.convertProductsToProductDtos(purchase.getProducts()));
        purchaseDto.setUserDto(userConverter.convertUserToUserDto(purchase.getCustomer()));
        purchaseDto.setPurchaseStatusDto(purchaseStatusConverter.convertPurchaseStatusToPurchaseStatusDto(purchase.getPurchaseStatus()));

        return purchaseDto;
    }

    public List<Purchase> convertPurchaseDtosToPurchases(List<PurchaseDto> purchasesDtos) {
        List<Purchase> purchases = new ArrayList<>();
        if (purchasesDtos != null){
            for (PurchaseDto purchaseDto : purchasesDtos){
                purchases.add(convertPurchaseDtoToPurchase(purchaseDto));
            }
        }
        return purchases;
    }

}
