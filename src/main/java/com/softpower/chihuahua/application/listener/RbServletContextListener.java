package com.softpower.chihuahua.application.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.softpower.chihuahua.RbSystemProperties;

@Slf4j
@WebListener
public class RbServletContextListener implements ServletContextListener {

	private WebApplicationContext applicationContext;

	private void initEnvironment(String environment) {
		// reserved
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		Properties props = (Properties) applicationContext.getBean("sysProperties");
		RbSystemProperties.setEnvironment(props.getProperty(RbSystemProperties.ENVIRONMENT));

		initEnvironment(RbSystemProperties.getEnvironment());

		log.info("contextInitialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// reserved
	}

}
