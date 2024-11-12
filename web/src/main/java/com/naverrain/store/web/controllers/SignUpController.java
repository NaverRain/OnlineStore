package com.naverrain.store.web.controllers;


import com.naverrain.core.services.Validator;
import com.naverrain.core.services.impl.DefaultValidator;
import com.naverrain.store.web.filters.PartnerCodeFilter;


import com.naverrain.core.facades.UserFacade;
import com.naverrain.persistence.entities.User;
import com.naverrain.persistence.entities.impl.DefaultUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);

	@Autowired
	private UserFacade userFacade;

	@Autowired
	private Validator validator;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String doGet(Model model){
		model.addAttribute("user", new DefaultUser());
		return "signup";
	}

	@PostMapping
	public String doPost(@Valid @ModelAttribute("user") DefaultUser user, BindingResult br, HttpSession session, HttpServletRequest request,
						 @CookieValue(value = PartnerCodeFilter.PARTNER_CODE_COOKIE_NAME, defaultValue = "null") String partnerCodeCookie){
		String notEncryptedPassword = user.getPassword();
		String repeatPassword = user.getRepeatPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		User userByEmail = userFacade.getUserByEmail(user.getEmail());


		if (notEncryptedPassword == null || repeatPassword == null || firstName == null || lastName == null){
			session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.empty.field", null, Locale.getDefault()));
			LOGGER.warn("Registration failed. User left empty field");
			return "redirect:/signup";
		}
		if (repeatPassword == null || !repeatPassword.equals(notEncryptedPassword)){
			session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.repeat.password", null, Locale.getDefault()));
			LOGGER.warn("Registration is failed. User passwords do not match");
			return "redirect:/signup";
		}
		if (userByEmail != null){
			session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.email.exists", null, Locale.getDefault()));
			LOGGER.warn("Registration is failed. User with email: {} is already exist.", user.getEmail());
			return "redirect:/signup";
		}
		if (!validator.isValidEmail(user.getEmail())){
			session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.bad.email", null, Locale.getDefault()));
			LOGGER.warn("Registration is failed. Bad email entered");
			return "redirect:/signup";
		}
		if (br.hasErrors()){
			return "signup";
		}

		user.setPassword(passwordEncoder.encode(notEncryptedPassword));

		List<String> errorMessages = validator.validate(notEncryptedPassword);

		if (errorMessages.size() != 0){
			String errMsg = messageSource.getMessage("signup.err.msg.general.error", null, Locale.getDefault());
			if (errorMessages.contains(DefaultValidator.MOST_COMMON_PASSWORD)){
				errMsg = messageSource.getMessage("signup.err.msg.common.password", null, Locale.getDefault());
				LOGGER.warn("Registration is failed. User entered most common password");
			}
			if (errorMessages.contains(DefaultValidator.LENGTH_OR_SPECIAL_CHARACTER_ERROR)){
				errMsg = messageSource.getMessage("signup.err.msg.special.character", null, Locale.getDefault());
				LOGGER.warn("Registration is failed. User entered a bad password");
			}
			session.setAttribute("errMsg", errMsg);
			return "redirect:/signup";
		}



		String partnerCode = null;
		if (!partnerCodeCookie.equals("null")){
			partnerCode = partnerCodeCookie;
			LOGGER.info("Partner code {} used for register user", partnerCode);
		}

		user.setIsEnabled(true);
		userFacade.registerUser(user, partnerCode);
		LOGGER.info("New user with email {} registered", user.getEmail());
		return "redirect:/signin";
	}

}
