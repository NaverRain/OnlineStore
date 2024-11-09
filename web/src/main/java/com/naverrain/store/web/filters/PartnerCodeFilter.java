package com.naverrain.store.web.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class PartnerCodeFilter implements Filter {

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

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}
