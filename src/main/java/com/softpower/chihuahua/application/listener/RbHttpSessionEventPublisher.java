package com.softpower.chihuahua.application.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.web.session.HttpSessionEventPublisher;

@Slf4j
@WebListener
public class RbHttpSessionEventPublisher extends HttpSessionEventPublisher {

	private int sessionCount = 0;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		super.sessionCreated(event);
		sessionCount++;
		log.debug("sessionCreated current count {}", sessionCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		super.sessionDestroyed(event);
		sessionCount--;
		log.debug("sessionDestroyed current count {}", sessionCount);
	}

}
