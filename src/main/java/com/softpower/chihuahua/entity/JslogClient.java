package com.softpower.chihuahua.entity;

import com.softpower.chihuahua.core.entity.RbEntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class JslogClient extends RbEntityBase {

	private String navigatorAppVersion;		// [Ex] 5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/44.0.2403.130 Safari/537.36
	private String navigatorCookieEnabled;	// [Ex] true
	private String navigatorLanguage;		// [Ex] zh-TW
	private String navigatorPlatform;		// [Ex] Win32
	private String navigatorUserAgent;		// [Ex] Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/44.0.2403.130 Safari/537.36
	private String navigatorVendor;			// [Ex] Google Inc.

	private String screenWidth;				// [Ex] 1920
	private String screenHeight;			// [Ex] 1080
	private String screenAvailWidth;		// [Ex] 1920
	private String screenAvailHeight;		// [Ex] 1040
	private String screenColorDepth;		// [Ex] 24
	private String screenPixelDepth;		// [Ex] 24

	private String locationHref;			// [Ex] http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action#08/11/2015
	private String locationHostname;		// [Ex] localhost
	private String locationPathname;		// [Ex] /AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action
	private String locationProtocol;		// [Ex] http:

	private String documentWidth;			// [Ex] 1169
	private String documentHeight;			// [Ex] 5615

}
