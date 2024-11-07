package com.naverrain.store.web.controllers;

import com.naverrain.store.web.Configurations;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet("/change-locale")
public class ChangeLocaleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("locale");

        if (language == null){
            Locale.setDefault(Locale.of(Configurations.DEFAULT_ENGLISH_LANGUAGE));
            request.getSession().setAttribute("locale", Configurations.DEFAULT_ENGLISH_LANGUAGE);
        }
        else if (language.equals(Configurations.FRENCH_LANGUAGE)){
            Locale.setDefault(Locale.of(Configurations.FRENCH_LANGUAGE));
            request.getSession().setAttribute("locale", Configurations.FRENCH_LANGUAGE);
        }

        String baseUrl = request.getScheme()
                        + "://"
                        + request.getServerName()
                        + ":"
                        + request.getServerPort()
                        + request.getServletContext().getContextPath();
        response.sendRedirect(baseUrl);
    }
}
