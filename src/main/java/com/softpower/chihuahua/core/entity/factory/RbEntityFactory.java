package com.softpower.chihuahua.core.entity.factory;

import org.joda.time.DateTime;

import com.softpower.chihuahua.core.entity.RbEntityBase;
import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;

public class RbEntityFactory {

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
