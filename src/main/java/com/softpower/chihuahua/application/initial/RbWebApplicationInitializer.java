package com.softpower.chihuahua.application.initial;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.springframework.web.WebApplicationInitializer;

@WebListener
public class RbWebApplicationInitializer implements WebApplicationInitializer {
	
	@Override
    public void onStartup(ServletContext container) {
		
    }
	
}
