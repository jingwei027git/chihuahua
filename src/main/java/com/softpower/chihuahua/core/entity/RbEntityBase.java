package com.softpower.chihuahua.core.entity;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * include {id}
 * <p>
 * <p>
 */
@Getter
@Setter
@SuppressWarnings("serial")
public abstract class RbEntityBase implements RbEntity {

	@Transient protected Long id;

	public static <T extends RbEntityBase> T createEntity(Class<T> entityClass) {
		try {
			T entity = entityClass.newInstance();
			return entity;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
