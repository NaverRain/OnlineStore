package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.PurchaseFacade;
import com.naverrain.core.facades.impl.DefaultPurchaseFacade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = "/management-fulfilment", name = "/fulfilment")
public class FulfilmentServlet extends HttpServlet {

    private PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        purchaseFacade.markFulfilmentStageForPurchaseIdAsCompleted(Integer.valueOf(request.getParameter("purchaseId")));
		String baseUrl = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ":"
				+ request.getServerPort()
				+ request.getServletContext().getContextPath();

        response.sendRedirect(baseUrl + "/management-orders");
    }
}
