package com.naverrain.store.web.filters;

import static com.naverrain.persistence.dto.RoleDto.*;
import static com.naverrain.store.web.controllers.SignInController.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.naverrain.persistence.entities.User;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
      

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = (User)((HttpServletRequest)request).getSession().getAttribute(LOGGED_IN_USER_ATTR);
		System.out.println(user);
		if (user != null) {
			if (user.getRoleName().equals(ADMIN_ROLE_NAME)) {
				chain.doFilter(request, response);
			}
			else {
				((HttpServletResponse)response).sendError(403);
			}
		}
		else {
			((HttpServletResponse)response).sendRedirect(
					request.getScheme()
					+ "://"
					+ request.getServerName()
					+ ":"
					+ request.getServerPort()
					+ request.getServletContext().getContextPath() + "/signin");
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
