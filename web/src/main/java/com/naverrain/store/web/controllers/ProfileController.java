package com.naverrain.store.web.controllers;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.persistence.entities.User;
import com.naverrain.store.web.filters.PartnerCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/profile")
    public String doGet(HttpSession session, HttpServletRequest request, Model model){
        User loggedInUser = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);

        if (loggedInUser != null){
            String baseUrl = request.getScheme()
                    + "://" + request.getServerName()
                    + ":" + request.getServerPort()
                    + request.getServletContext().getContextPath();

            String partnerLink = baseUrl + "?" + PartnerCodeFilter.PARTNER_CODE_PARAMETER_NAME
                    + "=" + loggedInUser.getPartnerCode();

            List<User> referrals = userFacade.getReferralsForUser(loggedInUser);
            loggedInUser = userFacade.getUserById(loggedInUser.getId());

            session.setAttribute(SignInController.LOGGED_IN_USER_ATTR, loggedInUser);

            model.addAttribute("referrals", referrals);
            model.addAttribute("partnerLink", partnerLink);

            return "profile";
        }
        else {
            return "signin";
        }
    }
}
