package com.softpower.chihuahua.core.entity;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * include {id}
 * <p>
 * <p>
 */
@SuppressWarnings("serial")
public abstract class RbEntityBase implements RbEntity {

	@Getter
	@Setter
	@Transient
	protected Long id;

}
