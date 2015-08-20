package com.softpower.chihuahua.core.entity;

import org.joda.time.DateTime;

import lombok.Getter;

/**
 * include {createUser, createTime, modifyUser, modifyTime}
 * <p>
 * <p>
 */
@Getter
@SuppressWarnings("serial")
public abstract class RbEntityLogTimeBase extends RbEntityBase {

	protected String createUser;
	protected DateTime createTime;
	protected String modifyUser;
	protected DateTime modifyTime;
	
	public <T extends RbEntityLogTimeBase> T init(final String createOrModifyUser) {
		return init(createOrModifyUser, DateTime.now());
	}
	
	public <T extends RbEntityLogTimeBase> T init(final String createOrModifyUser, final DateTime createOrModifyTime) {
		if (getId() == null) {
			createUser = createOrModifyUser;
			createTime = createOrModifyTime;
		}
		modifyUser = createOrModifyUser;
		modifyTime = createOrModifyTime;
		
		@SuppressWarnings("unchecked")
		T t = (T) this;
		
		return t;
	}

}
