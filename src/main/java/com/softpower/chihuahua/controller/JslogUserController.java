package com.softpower.chihuahua.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softpower.chihuahua.condition.JslogUserCond;
import com.softpower.chihuahua.core.controller.RbEntityController;
import com.softpower.chihuahua.core.service.RbEntityService;
import com.softpower.chihuahua.entity.JslogUser;
import com.softpower.chihuahua.service.JslogUserService;

@RestController
@RequestMapping("/user")
public class JslogUserController extends RbEntityController<JslogUser, JslogUserCond> {

	@Resource(name = "rbUserService")
	protected JslogUserService rbUserService;

	@Override
	protected RbEntityService<JslogUser, JslogUserCond, Long> getRbEntityService() {
		return rbUserService;
	}

}
