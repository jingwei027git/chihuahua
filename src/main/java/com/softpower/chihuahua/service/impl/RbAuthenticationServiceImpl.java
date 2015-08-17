package com.softpower.chihuahua.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.dao.RbOperationDao;
import com.softpower.chihuahua.dao.RbRoleDao;
import com.softpower.chihuahua.dao.RbUserDao;
import com.softpower.chihuahua.entity.RbOperation;
import com.softpower.chihuahua.entity.RbRole;
import com.softpower.chihuahua.entity.RbUser;

@Component("RbAuthenticationService")
public class RbAuthenticationServiceImpl implements UserDetailsService {

	@Resource(name="RbUserDao")
    private RbUserDao userDao;

	@Resource(name="RbRoleDao")
	private RbRoleDao roleDao;
	
	@Resource(name="RbOperationDao")
	private RbOperationDao operationDao;
	
	@Resource(name="RoleHierarchy")
	private RoleHierarchy roleHierarchy;

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

	private Collection<? extends GrantedAuthority> getAuthorities(Long userId) {
		List<GrantedAuthority> roleAuthorities = getAuthoritiesByRoles(getRoles(userId));
		List<GrantedAuthority> operAuthorities = getAuthoritiesByOperations(getOperations(userId));
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.addAll(roleAuthorities);
		authorities.addAll(operAuthorities);
		
		if (roleHierarchy != null) {
			return roleHierarchy.getReachableGrantedAuthorities(authorities);
		}else{
			return authorities;
		}
    }

	private List<RbRole> getRoles(Long userId) {
		return roleDao.findByUserIdAndSysStatus(userId, YesNo.Y);
	}
	
	private List<RbOperation> getOperations(Long userId) {
		return operationDao.findByUserIdAndSysStatus(userId, YesNo.Y);
	}

	private List<GrantedAuthority> getAuthoritiesByRoles(List<RbRole> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RbRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		return authorities;
	}
	
	private List<GrantedAuthority> getAuthoritiesByOperations(List<RbOperation> operations) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RbOperation operation : operations) {
			authorities.add(new SimpleGrantedAuthority(operation.getCode()));
		}
		return authorities;
	}

}
