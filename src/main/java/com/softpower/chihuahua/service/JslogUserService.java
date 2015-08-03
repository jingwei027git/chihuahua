package com.softpower.chihuahua.service;

import java.util.List;

import com.softpower.chihuahua.condition.JslogUserCond;
import com.softpower.chihuahua.core.service.RbEntityService;
import com.softpower.chihuahua.entity.JslogUser;

public interface JslogUserService extends RbEntityService<JslogUser, JslogUserCond, Long> {

	public List<JslogUser> getByUsername(String username);

	public List<JslogUser> getByEmail(String email);

}
