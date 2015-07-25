package com.softpower.chihuahua.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.softpower.chihuahua.core.entity.RbEntityBase;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class JslogScreenshot extends RbEntityBase {

	private String mimetype;
	private byte[] stream;

}
