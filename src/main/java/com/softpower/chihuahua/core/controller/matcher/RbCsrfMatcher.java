package com.softpower.chihuahua.core.controller.matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.web.util.matcher.RequestMatcher;


@Slf4j
public class RbCsrfMatcher implements RequestMatcher {

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	@Getter @Setter
	private List<String> excludeUrls = new ArrayList<>();

	private boolean isExcludeUrl(HttpServletRequest request) {
		final String contextPath = request.getContextPath();
		for (String excludeUrl : excludeUrls) {
			if ((contextPath + excludeUrl).startsWith(request.getRequestURI())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		boolean matchesToCsrf = !allowedMethods.matcher(request.getMethod()).matches();
		if (matchesToCsrf) {
			log.info(request.getRequestURI());
			return !isExcludeUrl(request);
		}else {
			return matchesToCsrf;
		}
	}

}
