package com.naverrain.store.web.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpResponse;

@WebFilter("/*")
public class PartnerCodeFilter extends HttpFilter implements Filter {

	public final static String PARTNER_CODE_PARAMETER_NAME = "partner_code";
	public final static String PARTNER_CODE_COOKIE_NAME = "partner_code";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String partnerCode = request.getParameter(PARTNER_CODE_PARAMETER_NAME);
		if (partnerCode != null && !partnerCode.isEmpty()) {
			((HttpServletResponse)response)
				.addCookie(new Cookie(PARTNER_CODE_COOKIE_NAME, partnerCode));
		}
		chain.doFilter(request, response);
	}

}
