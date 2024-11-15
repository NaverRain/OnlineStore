package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.PurchaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FulfilmentController {

    @Autowired
    private PurchaseFacade purchaseFacade;

    @PostMapping("/management-fulfilment")
    public String doPost(@RequestParam Integer purchaseId){
        purchaseFacade.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);
        return "redirect:/management-orders";
    }
}
