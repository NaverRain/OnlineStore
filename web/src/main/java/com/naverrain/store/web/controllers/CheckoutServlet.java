package com.naverrain.store.web.controllers;


import com.naverrain.core.facades.ProductFacade;
import com.naverrain.core.facades.PurchaseFacade;
import com.naverrain.core.facades.impl.DefaultProductFacade;
import com.naverrain.core.facades.impl.DefaultPurchaseFacade;
import com.naverrain.persistence.entities.User;
import com.naverrain.store.web.Configurations;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private PurchaseFacade purchaseFacade = DefaultPurchaseFacade.getInstance();
    private ProductFacade productFacade = DefaultProductFacade.getInstance();
    private ResourceBundle rb = ResourceBundle.getBundle(Configurations.RESOURCE_BUNDLE_BASE_NAME);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productGuid = request.getParameter("guid");
        purchaseFacade.createPurchase( (User) request.getSession().getAttribute(SignInServlet.LOGGED_IN_USER_ATTR),
                        productFacade.getProductByGuid(productGuid));

        String baseUrl = request.getScheme()
                            + "://"
                            + request.getServerName()
                            + ":"
                            + request.getServerPort()
                            + request.getServletContext().getContextPath();

        request.getSession().setAttribute("olderStatus", rb.getString("order.created.msg"));

        response.sendRedirect(baseUrl + "/product?guid=" + productGuid);
    }


}
