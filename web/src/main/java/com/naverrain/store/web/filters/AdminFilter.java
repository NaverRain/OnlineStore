package com.naverrain.store.web.filters;

import static com.naverrain.persistence.dto.RoleDto.*;
import static com.naverrain.store.web.controllers.SignInServlet.*;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.naverrain.persistence.entities.User;

@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter implements Filter {
      

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



}
