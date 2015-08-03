package com.softpower.chihuahua.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softpower.chihuahua.condition.RbUserCond;
import com.softpower.chihuahua.core.controller.RbEntityRestController;
import com.softpower.chihuahua.core.service.RbEntityService;
import com.softpower.chihuahua.entity.RbUser;
import com.softpower.chihuahua.service.RbUserService;

@RestController
@RequestMapping("/users")
public class RbUserController extends RbEntityRestController<RbUser, RbUserCond> {

	@Resource(name = "RbUserService")
	protected RbUserService rbUserService;

	@Override
	protected RbEntityService<RbUser, RbUserCond, Long> getRbEntityService() {
		return rbUserService;
	}

}
