package com.softpower.chihuahua.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.softpower.chihuahua.entity.RbUser;

public abstract class RbControllerBase implements RbController {

	public UserDetails getPrincipalUser() {
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

		RbUser unknowUser = new RbUser();
		unknowUser.setUsername("unknowUser");
		return unknowUser;
	}

}
