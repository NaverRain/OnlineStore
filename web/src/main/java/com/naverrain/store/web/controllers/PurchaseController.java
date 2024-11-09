package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.PurchaseFacade;
import com.naverrain.persistence.entities.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseFacade purchaseFacade;

    @GetMapping("/management-orders")
    public String doGet(Model model){
        List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
        model.addAttribute("purchases", purchases);
        return "orders";
    }
}
