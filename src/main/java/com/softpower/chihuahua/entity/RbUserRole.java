package com.softpower.chihuahua.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.softpower.chihuahua.core.entity.RbEntityBase;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class RbUserRole extends RbEntityBase {
	private Long userId;	// RbUser.id
	private Long roleId;	// RbRole.id
}
