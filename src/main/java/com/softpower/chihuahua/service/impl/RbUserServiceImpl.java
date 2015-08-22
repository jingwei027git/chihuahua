package com.softpower.chihuahua.service.impl;

import javax.annotation.Resource;

import lombok.Getter;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.condition.RbUserCond;
import com.softpower.chihuahua.core.service.RbEntityServiceImpl;
import com.softpower.chihuahua.dao.RbUserDao;
import com.softpower.chihuahua.entity.RbUser;
import com.softpower.chihuahua.service.RbUserService;

@Getter
@Component("RbUserService")
public class RbUserServiceImpl extends RbEntityServiceImpl<RbUser, RbUserCond, RbUserDao> implements RbUserService {

	@Resource(name="RbUserDao")
	protected RbUserDao rbUserDao;

	@Override
	public RbUserDao getDao() {
		return rbUserDao;
	}

	@Override
	public RbUser getByUsername(String username) {
		return getDao().findByUsername(username);
	}

	@Override
	public RbUser getByEmail(String email) {
		return getDao().findByEmail(email);
	}

}
