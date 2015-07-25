package com.softpower.chihuahua.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class JslogFeedback extends RbEntityLogTimeBase {

	private Long appId;
	private Long clientId;
	private Long screenshotId;

	private String subject;
	private String content;

}
