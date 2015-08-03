package com.softpower.chihuahua.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;
import com.softpower.chihuahua.core.enums.YesNo;

/**
 * @see org.springframework.security.core.userdetails.UserDetails
 */
@Getter
@Setter
@ToString(exclude = "password")
@SuppressWarnings("serial")
public class RbUser extends RbEntityLogTimeBase implements UserDetails {
	private YesNo sysStatus;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String description;
	private YesNo lockStatus;
	private String password;
	private Integer loginCount;
	private Integer errorCount;
	private Integer continueErrorCount;
	private DateTime lastChangePasswordTime;
	private DateTime firstLoginTime;
	private DateTime lastLoginTime;
	private DateTime expireTime;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return (expireTime.getMillis() >= System.currentTimeMillis());
	}

	@Override
	public boolean isAccountNonLocked() {
		return (YesNo.N == getLockStatus());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return (YesNo.Y == getSysStatus());
	}

}
