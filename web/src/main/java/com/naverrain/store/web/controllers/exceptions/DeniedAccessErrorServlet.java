package com.naverrain.store.web.controllers.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.naverrain.store.web.Configurations;

@WebServlet("/403-error")
public class DeniedAccessErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "403page.jsp").forward(request, response);
	}

}
