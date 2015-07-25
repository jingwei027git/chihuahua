package com.softpower.chihuahua.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.softpower.chihuahua.core.entity.RbEntityBase;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class JslogClient extends RbEntityBase {

	private String navigatorAppVersion;
	private String navigatorCookieEnabled;
	private String navigatorLanguage;
	private String navigatorPlatform;
	private String navigatorUserAgent;
	private String navigatorVendor;

	private String screenWidth;
	private String screenHeight;
	private String screenAvailWidth;
	private String screenAvailHeight;
	private String screenColorDepth;
	private String screenPixelDepth;

	private String locationHref;
	private String locationHostname;
	private String locationPathname;
	private String locationProtocol;

	private String documentWidth;
	private String documentHeight;

}
