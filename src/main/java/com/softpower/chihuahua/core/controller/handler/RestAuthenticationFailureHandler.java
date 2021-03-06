package com.softpower.chihuahua.core.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * for RESTful stateful
 * <p>
 * <p>
 */
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception)
			throws IOException, ServletException
	{
		if (exception != null &&
			BadCredentialsException.class.isAssignableFrom(exception.getClass())) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}else{
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}
