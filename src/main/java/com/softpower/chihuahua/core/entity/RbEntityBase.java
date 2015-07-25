package com.softpower.chihuahua.core.entity;

import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public abstract class RbEntityBase implements RbEntity {

	@Transient
	protected Long id;

}
