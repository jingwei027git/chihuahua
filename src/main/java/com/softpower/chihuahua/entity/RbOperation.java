package com.softpower.chihuahua.entity;

import org.springframework.security.core.GrantedAuthority;

import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;
import com.softpower.chihuahua.core.enums.YesNo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class RbOperation extends RbEntityLogTimeBase implements GrantedAuthority {
	private YesNo sysStatus;
	private String code;
	private String name;
	private String description;
	
	@Override
	public String getAuthority() {
		return code;
	}
	
}
