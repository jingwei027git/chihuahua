package com.softpower.chihuahua.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.condition.JslogUserCond;
import com.softpower.chihuahua.core.service.RbEntityServiceImpl;
import com.softpower.chihuahua.dao.JslogUserDao;
import com.softpower.chihuahua.entity.JslogUser;
import com.softpower.chihuahua.service.JslogUserService;

@Getter
@Component("JslogUserService")
@SuppressWarnings("serial")
public class JslogUserServiceImpl extends RbEntityServiceImpl<JslogUser, JslogUserCond, JslogUserDao> implements JslogUserService {

	@Resource(name="JslogUserDao")
	protected JslogUserDao rbUserDao;

	@Override
	public JslogUserDao getDao() {
		return rbUserDao;
	}

	@Override
	public List<JslogUser> getByUsername(String username) {
		return getDao().findByUsername(username);
	}

	@Override
	public List<JslogUser> getByEmail(String email) {
		return getDao().findByEmail(email);
	}

}
