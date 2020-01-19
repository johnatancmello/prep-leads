package com.prep.api.token;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//filtra a requisição para um refresh token
//pegando o refresh token do cookie e adicionando no corpo (parameter)

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		
		if (isRefreshToken(req)) {
			Cookie cookie = getRefreshToken(req.getCookies());
			if (cookie != null) {
				req = new MyServletRequestWrapper(req, cookie.getValue());
			}
			
		}

		chain.doFilter(req, response);
	}
	


	//AVISO: pode retornar null
	private Cookie getRefreshToken(Cookie[] cookies) {
		for(Cookie cookie : cookies) {
			if (equalsRefreshToken(cookie)) {
				return cookie;
			}
		}
		return null;
	}
	
	private boolean isRefreshToken(HttpServletRequest req) {
		return ("/oauth/token".equalsIgnoreCase(req.getRequestURI()) 
				&& "refresh_token".equals(req.getParameter("grant_type"))
				&& req.getCookies() != null);
	}
	
	private boolean equalsRefreshToken(Cookie cookie) {
		return cookie.getName().equals("refreshToken");
	}
	
}
