package com.softpower.chihuahua.core.controller.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.softpower.chihuahua.entity.RbUser;

public class RbSpringSecurity {
	
	public static UserDetails generateUnknowUser() {
		RbUser user = new RbUser();
		user.setUsername("unknowUser");
		return user;
	}
	
	public static UserDetails getPrincipalUser() {
		final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal != null) {
			if (principal instanceof UserDetails) {
				return (UserDetails) principal;
			} else if (principal instanceof String) {
				RbUser anonymousUser = new RbUser();
				anonymousUser.setUsername(principal.toString());
				return anonymousUser;
			}
		}

		return generateUnknowUser();
	}

}
