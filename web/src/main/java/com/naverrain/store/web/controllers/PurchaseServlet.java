package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.PurchaseFacade;
import com.naverrain.core.facades.impl.DefaultPurchaseFacade;
import com.naverrain.persistence.entities.Purchase;
import com.naverrain.store.web.Configurations;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/management-orders", name = "purchase")
public class PurchaseServlet extends HttpServlet {

    private PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Purchase> purchases = purchaseFacade.getNotCompletedPurchases();
        request.setAttribute("purchases", purchases);

        request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "orders.jsp").forward(request, response);
    }
}
