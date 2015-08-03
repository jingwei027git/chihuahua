package com.softpower.chihuahua.core.entity;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

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

	@Deprecated
	public static <T extends RbEntityBase> T createEntity(Class<T> entityClass) {
		throw new RuntimeException("not supported");
	}

	public static <T extends RbEntityBase> T createEntity(Class<T> entityClass, final String createUser) {
		return createEntity(entityClass, createUser, DateTime.now());
	}

	public static <T extends RbEntityBase> T createEntity(Class<T> entityClass, final String createUser, final DateTime createTime) {
		try {
			T entity = entityClass.newInstance();
			if (entity instanceof RbEntityLogTimeBase) {
				((RbEntityLogTimeBase) entity).setCreateUser(createUser);
				((RbEntityLogTimeBase) entity).setCreateTime(createTime);
				((RbEntityLogTimeBase) entity).setModifyUser(createUser);
				((RbEntityLogTimeBase) entity).setModifyTime(createTime);
			}
			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
