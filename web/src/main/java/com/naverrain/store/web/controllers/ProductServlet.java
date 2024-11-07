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

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductFacade productFacade = DefaultProductFacade.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = productFacade.getProductByGuid(request.getParameter("guid"));

        request.setAttribute("product", product);
        request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "pdp.jsp").forward(request, response);
    }
}
