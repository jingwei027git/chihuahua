package com.softpower.chihuahua.entity;

import com.softpower.chihuahua.core.entity.RbEntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class RbRoleOperation extends RbEntityBase {
	private Long roleId;		// RbRole.id
	private Long operationId;	// RbOperation.id
}
