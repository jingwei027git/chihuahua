package com.softpower.chihuahua.core.controller.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * for RESTful stateful
 * <p>
 * <p>
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException)
			throws IOException, ServletException
	{
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		invalidateSession(request.getSession(false));
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

	private void invalidateSession(HttpSession session) {
		if (session != null) {
			try {
				session.invalidate();
			} catch (Exception e) {
				// ignore
			}
		}
	}

}
