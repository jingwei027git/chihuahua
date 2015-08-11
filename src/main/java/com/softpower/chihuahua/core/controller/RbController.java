package com.softpower.chihuahua.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import com.softpower.chihuahua.entity.RbUser;

public interface RbController {
	
	default public RbUser getPrincipalUser() {
		return (RbUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
