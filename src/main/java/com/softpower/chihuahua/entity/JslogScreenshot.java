package com.softpower.chihuahua.entity;

import com.softpower.chihuahua.core.entity.RbEntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "base64")
@SuppressWarnings("serial")
public class JslogScreenshot extends RbEntityBase {

	private String mimetype;	// [Ex] image/png
	private String base64;		// [Ex] 

}
