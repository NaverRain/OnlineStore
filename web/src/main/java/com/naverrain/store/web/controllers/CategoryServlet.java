package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.ProductFacade;
import com.naverrain.core.facades.impl.DefaultProductFacade;
import com.naverrain.persistence.entities.Product;
import com.naverrain.store.web.Configurations;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private ProductFacade productFacade = DefaultProductFacade.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer categoryId = Integer.valueOf(request.getParameter("id"));
        Integer page = Integer.valueOf(request.getParameter("page"));

        List<Product> products = productFacade.getProductsByCategoryIdForPageWithLimit(categoryId, page, Configurations.PAGINATION_LIMIT);
        Integer numberOfPagesForCategory = productFacade.getNumberOfPagesForCategory(categoryId, Configurations.PAGINATION_LIMIT);

        List<Integer> pages = IntStream.range(1, numberOfPagesForCategory + 1).boxed().collect(Collectors.toList());

        request.setAttribute("products", products);
        request.setAttribute("pages", pages);
        request.setAttribute("activePage", page);
        request.setAttribute("categoryId", categoryId);

        request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "plp.jsp").forward(request, response);
    }


}
