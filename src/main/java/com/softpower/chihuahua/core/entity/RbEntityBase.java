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

}
