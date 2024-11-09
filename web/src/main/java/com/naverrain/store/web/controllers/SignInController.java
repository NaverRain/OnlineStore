package com.naverrain.store.web.controllers;

import static com.naverrain.persistence.dto.RoleDto.*;

import com.naverrain.store.web.utils.PBKDF2WithHmacSHA1EncryptionService;

import javax.servlet.http.HttpSession;

import java.io.IOException;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.persistence.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signin")
public class SignInController {

	private static final String UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY = "UNSUCCESSFUL_LOGIN_COUNT";
	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";
	private static final Logger LOGGER = LoggerFactory.getLogger(SignInController.class);

	@Autowired
	private PBKDF2WithHmacSHA1EncryptionService encryptionService;

	@Autowired
	private UserFacade userFacade;

	@GetMapping
	public String doGet(){
		return "/signin";
	}

	@PostMapping
	public String  doPost(@RequestParam String email, HttpSession session, @RequestParam String password){
		User user = userFacade.getUserByEmail(email);
		LOGGER.info("Session is required");

		if (user != null && encryptionService.validatePassword(password, user.getPassword())){
			session.setAttribute(LOGGED_IN_USER_ATTR, user);

			LOGGER.info("User with Id: {} added to the session", user.getId());
			if (user.getRoleName().equals(ADMIN_ROLE_NAME)){
				LOGGER.info("Admin with Id: {} redirected to the admin panel", user.getId());
				return "redirect:/admin-panel";
			}
			else if (user.getRoleName().equals(MANAGER_ROLE_NAME)){
				LOGGER.info("Manager with Id: {} redirected to the manager panel", user.getId());
				return "redirect:/management-orders";
			}
			else {
				LOGGER.info("User with Id: {} is redirected to the homepage", user.getId());
				return "redirect:/homepage";
			}
		}
		else {
			Integer failedLoginCounter = (Integer) session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY);
			session.setAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY,
					failedLoginCounter == null ? 1 : failedLoginCounter++);
			LOGGER.warn("Unsuccessful login attempt #{}", (Integer)session.getAttribute(UNSUCCESSFUL_LOGIN_COUNT_ATTR_KEY));
			return "redirect:/signin";
		}
	}


}
