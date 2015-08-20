package com.softpower.chihuahua.core.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.softpower.chihuahua.core.controller.security.RbSpringSecurity;

import lombok.Getter;

public abstract class RbControllerBase implements RbController {
	
	@Getter
	@Autowired
	private HttpServletRequest request;
	
	@Getter
	@Autowired
	private HttpServletResponse response;
	

	@PostConstruct
	public void init() {
		
	}
	
	protected UserDetails getPrincipalUser() {
		return RbSpringSecurity.getPrincipalUser();
	}

}
