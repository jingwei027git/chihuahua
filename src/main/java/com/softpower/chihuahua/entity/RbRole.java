package com.softpower.chihuahua.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;
import com.softpower.chihuahua.core.enums.YesNo;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class RbRole extends RbEntityLogTimeBase {
	private YesNo sysStatus;
	private String code;
	private String name;
	private String description;
}
