package com.naverrain.store.web.filters;

import com.naverrain.persistence.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.naverrain.persistence.dto.RoleDto.MANAGER_ROLE_NAME;
import static com.naverrain.store.web.controllers.SignInController.LOGGED_IN_USER_ATTR;

@WebFilter(urlPatterns = {"/management-fulfilment", "/management-orders"})
public class ManagementFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        User user = (User)((HttpServletRequest)request).getSession().getAttribute(LOGGED_IN_USER_ATTR);
        if (user != null){
            if (user.getRoleName().equals(MANAGER_ROLE_NAME)){
                filter.doFilter(request, response);
            }
            else {
                ((HttpServletResponse)response).sendError(403);
            }
        }
        else {
            ((HttpServletResponse)response).sendRedirect(
                    request.getScheme()
                    + "://" + request.getServerName()
                    + ":" + request.getServerPort()
                    + request.getServletContext().getContextPath() + "/signin"
            );
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
