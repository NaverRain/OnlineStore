package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.core.services.Validator;
import com.naverrain.core.services.impl.DefaultValidator;
import com.naverrain.persistence.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/edit-profile")
public class EditProfileController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private Validator validator;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public String doGet(HttpSession session){
        User loggedInUser = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);

        if (loggedInUser == null){
            return "redirect:/signin";
        }
        else {
            return "editProfile";
        }
    }

    @PostMapping
    public String doPost(HttpSession session, @RequestParam String firstName, @RequestParam String lastName,
                         @RequestParam String email, @RequestParam String password, @RequestParam("newPassword") String newPasswordParam){
        User loggedInUser = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
        User user = userFacade.getUserById(loggedInUser.getId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        User userByEmail = userFacade.getUserByEmail(user.getEmail());

        if (userByEmail != null && !email.equals(loggedInUser.getEmail())) {
            session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.email.exists", null, Locale.getDefault()));
            return "redirect:/edit-profile";
        }

        if (!encoder.matches(password, loggedInUser.getPassword())) {
            session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.old.password.wrong", null, Locale.getDefault()));
            return "redirect:/edit-profile";
        }

        if (newPasswordParam != null && !newPasswordParam.isEmpty()) {
            List<String> errorMessages = validator.validate(newPasswordParam);
            if (errorMessages.size() != 0) {
                String errMsg = messageSource.getMessage("signup.err.msg.general.error", null, Locale.getDefault());
                if (errorMessages.contains(DefaultValidator.LENGTH_OR_SPECIAL_CHARACTER_ERROR)) {
                    errMsg = messageSource.getMessage("signup.err.msg.special.character", null, Locale.getDefault());
                }
                if (errorMessages.contains(DefaultValidator.MOST_COMMON_PASSWORD)) {
                    errMsg = messageSource.getMessage("signup.err.msg.common.password", null, Locale.getDefault());
                }
                session.setAttribute("errMsg", errMsg);
                return "redirect:/edit-profile";
            }
        }

        if (newPasswordParam != null && !newPasswordParam.isEmpty()) {
            user.setPassword(encoder.encode(newPasswordParam));
        }

        userFacade.updateUser(user);
        return "redirect:/profile";

    }

}
