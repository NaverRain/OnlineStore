package com.naverrain.store.web.filters;

import com.naverrain.core.facades.UserFacade;
import com.naverrain.persistence.entities.User;
import com.naverrain.store.web.security.DefaultAuthSuccessHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class RememberMeFilter implements Filter {

    private UserFacade userFacade;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(filterConfig.getServletContext());
        this.userFacade = context.getBean(UserFacade.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();

        if (session.getAttribute(DefaultAuthSuccessHandler.LOGGED_IN_USER_ATTR) == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // If there is authenticated user - let's add it to the current session
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                User user = userFacade.getUserByEmail(authentication.getName());
                if (user != null) {
                    session.setAttribute(DefaultAuthSuccessHandler.LOGGED_IN_USER_ATTR, user);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
