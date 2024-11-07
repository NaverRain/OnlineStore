package com.naverrain.store.web.controllers;

import static com.naverrain.persistence.dto.RoleDto.*;

import com.naverrain.store.web.utils.PBKDF2WithHmacSHA1EncryptionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.core.facades.impl.DefaultUserFacade;
import com.naverrain.persistence.entities.User;
import com.naverrain.store.web.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY = "UNSUCCESSFUL_LOGIN_COUNT";
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";

	private static final Logger LOGGER = LoggerFactory.getLogger(SignInServlet.class);

	private PBKDF2WithHmacSHA1EncryptionService encryptionService = PBKDF2WithHmacSHA1EncryptionService.getInstance();

	private UserFacade userFacade;
	
	{
		userFacade = new DefaultUserFacade().getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(Configurations.VIEWS_PATH_RESOLVER 
				+ "signin.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = userFacade.getUserByEmail(request.getParameter("email"));
		String baseUrl = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ ":"
				+ request.getServerPort()
				+ request.getServletContext().getContextPath();
		HttpSession session = request.getSession();

		LOGGER.info("Session is requested");

		if (user != null && encryptionService.validatePassword(request.getParameter("password"), user.getPassword())) {
			session.setAttribute(LOGGED_IN_USER_ATTR, user);
			LOGGER.info("User with ID {} is added to the session", user.getId());
			if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
				response.sendRedirect(baseUrl + "/admin/panel");
				LOGGER.info("User with ID {} is redirected to the admin panel", user.getId());
				return;
			} else {
				response.sendRedirect(baseUrl + "/homepage");
				LOGGER.info("User with ID {} is redirected to the homepage", user.getId());
				return;
			}
		} else {
			Integer failedLoginCounter = (Integer)session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY);
			session.setAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY,
					failedLoginCounter == null ? 1 : ++failedLoginCounter);
			LOGGER.warn("Unsuccessful login attempt #{}", (Integer)session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY));
			response.sendRedirect(baseUrl + "/signin");
			return;
		}

	}

}
