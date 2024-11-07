package com.naverrain.store.web.controllers;

import java.io.IOException;
import java.util.List;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.core.facades.impl.DefaultUserFacade;
import com.naverrain.persistence.entities.User;
import com.naverrain.store.web.Configurations;

import com.naverrain.store.web.filters.PartnerCodeFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet{

	private UserFacade userFacade = DefaultUserFacade.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedInUser = ( (User) request.getSession().getAttribute(SignInServlet.LOGGED_IN_USER_ATTR));

		if (loggedInUser != null){
			String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletContext().getContextPath();
			String partnerLink = baseUrl + "?"
						+ PartnerCodeFilter.PARTNER_CODE_COOKIE_NAME
						+ "=" + loggedInUser.getPartnerCode();

			List<User> referrals = userFacade.getReferralsForUser(loggedInUser);
			loggedInUser = userFacade.getUserById(loggedInUser.getId());

			request.getSession().setAttribute(SignInServlet.LOGGED_IN_USER_ATTR, loggedInUser);

			request.setAttribute("referrals", referrals);
			request.setAttribute("partnerLink", partnerLink);
			request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER + "/profile.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER
					+ "/signin.jsp").forward(request, response);;
		}
	}
}
