package com.softpower.chihuahua.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;
import com.softpower.chihuahua.core.enums.YesNo;

@Getter
@Setter
@ToString(exclude = "password")
@SuppressWarnings("serial")
public class JslogUser extends RbEntityLogTimeBase {

	private YesNo sysStatus;
	private String name;
	private String email;
	private String password;

}
