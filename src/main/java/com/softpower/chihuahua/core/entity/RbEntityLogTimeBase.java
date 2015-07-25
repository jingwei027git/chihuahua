package com.softpower.chihuahua.core.entity;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

@Getter
@Setter
@SuppressWarnings("serial")
public abstract class RbEntityLogTimeBase extends RbEntityBase {

	protected String createUser;
	protected DateTime createTime;
	protected String modifyUser;
	protected DateTime modifyTime;

}
