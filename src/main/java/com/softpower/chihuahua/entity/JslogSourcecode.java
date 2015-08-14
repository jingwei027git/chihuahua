package com.softpower.chihuahua.entity;

import com.softpower.chihuahua.core.entity.RbEntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "content")
@SuppressWarnings("serial")
public class JslogSourcecode extends RbEntityBase {

	private String mimetype;	// [Ex] image/png
	private String content;		// [Ex] 

}
