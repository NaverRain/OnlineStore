package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.ProductFacade;
import com.naverrain.persistence.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SearchController {

    @Value("${pagination.limit}")
    private Integer paginationLimit;

    @Autowired
    private ProductFacade productFacade;

    @GetMapping("/search")
    public String doGet(@RequestParam String searchQuery, @RequestParam Integer page, Model model){
        List<Product> products = productFacade.getProductsLikeNameForPageWithLimit(searchQuery, page, paginationLimit);
        Integer numberOfPagesForSearch = productFacade.getNumberOfPagesForSearch(searchQuery, paginationLimit);
        List<Integer> pages = IntStream.range(1, numberOfPagesForSearch + 1).boxed().collect(Collectors.toList());

        model.addAttribute("products", products);
        model.addAttribute("pages", pages);
        model.addAttribute("activePage", page);
        model.addAttribute("searchQuery", searchQuery);

        return "plp";
    }
}
