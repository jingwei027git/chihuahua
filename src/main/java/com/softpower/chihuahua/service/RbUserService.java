package com.softpower.chihuahua.service;

import com.softpower.chihuahua.condition.RbUserCond;
import com.softpower.chihuahua.core.service.RbEntityService;
import com.softpower.chihuahua.entity.RbUser;

public interface RbUserService extends RbEntityService<RbUser, RbUserCond, Long> {

	public RbUser getByUsername(String username);

	public RbUser getByEmail(String email);

}
