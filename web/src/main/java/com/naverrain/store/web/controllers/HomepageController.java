package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.CategoryFacade;
import com.naverrain.persistence.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomepageController {

    @Autowired
    private CategoryFacade categoryFacade;

    @GetMapping(value = {"/homepage", "/"})
    public String doGet(Model model){
        List<Category> categories = categoryFacade.getCategories();
        model.addAttribute("categories", categories);
        return "homepage";
    }

}
