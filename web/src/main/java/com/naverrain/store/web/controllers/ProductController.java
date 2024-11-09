package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.ProductFacade;
import com.naverrain.persistence.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping("/product")
    public String doGet(@RequestParam String guid, Model model){
        Product product = productFacade.getProductByGuid(guid);
        model.addAttribute("product", product);
        return "pdp";
    }
}
