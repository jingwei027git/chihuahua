package com.softpower.chihuahua.entity;

import com.softpower.chihuahua.core.entity.RbEntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class RbUserOperation extends RbEntityBase {
	private Long userId;		// RbUser.id
	private Long operationId;	// RbOperation.id
}
