package com.softpower.chihuahua.core.entity;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * include {createUser, createTime, modifyUser, modifyTime}
 * <p>
 * <p>
 */
@Getter
@Setter
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
			setCreateUser(createOrModifyUser);
			setCreateTime(createOrModifyTime);
		}
		setModifyUser(createOrModifyUser);
		setModifyTime(createOrModifyTime);
		
		@SuppressWarnings("unchecked")
		T t = (T) this;
		
		return t;
	}

}
