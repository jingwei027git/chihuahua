package com.softpower.chihuahua.entity;

import com.softpower.chihuahua.core.entity.RbEntityBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class JslogAppUser extends RbEntityBase {
	private Long appId;	// JslogApp.id
	private Long userId; // JslogUser.id
}
