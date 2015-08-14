package com.softpower.chihuahua.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.dao.RbRoleDao;
import com.softpower.chihuahua.dao.RbUserDao;
import com.softpower.chihuahua.entity.RbRole;
import com.softpower.chihuahua.entity.RbUser;

@Component("RbAuthenticationService")
public class RbAuthenticationServiceImpl implements UserDetailsService {

	@Resource(name="RbUserDao")
    private RbUserDao userDao;

	@Resource(name="RbRoleDao")
	private RbRoleDao roleDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final RbUser user = userDao.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		UserDetails userDetails = new User(
			user.getUsername(),
			user.getPassword(),
			user.isEnabled(),
			user.isAccountNonExpired(),
			user.isCredentialsNonExpired(),
			user.isAccountNonLocked(),
			getAuthorities(user.getId())
		);

		return userDetails;
	}

	public Collection<? extends GrantedAuthority> getAuthorities(Long userId) {
        return getGrantedAuthorities(getRoles(userId));
    }

	public List<RbRole> getRoles(Long userId) {
		return roleDao.findByUserId(userId);
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<RbRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (RbRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}

		return authorities;
	}

}
