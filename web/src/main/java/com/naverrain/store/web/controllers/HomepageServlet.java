package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.CategoryFacade;
import com.naverrain.core.facades.impl.DefaultCategoryFacade;
import com.naverrain.persistence.entities.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.naverrain.store.web.Configurations;

@WebServlet("/homepage")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryFacade categoryFacade = DefaultCategoryFacade.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = categoryFacade.getCategories();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "homepage.jsp").forward(request, response);
	}


}
