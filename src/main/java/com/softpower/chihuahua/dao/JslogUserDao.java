package com.softpower.chihuahua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.JslogUser;

@Component("JslogUserDao")
public interface JslogUserDao extends RbEntityDao<JslogUser, Long> {

	public List<JslogUser> findByUsername(@Param("username") String username);

	public List<JslogUser> findByEmail(@Param("email") String email);

}
