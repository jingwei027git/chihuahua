package com.softpower.chihuahua.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.RbUser;

@Component("RbUserDao")
public interface RbUserDao extends RbEntityDao<RbUser, Long> {

	public RbUser findByUsername(@Param("username") String username);

	public RbUser findByEmail(@Param("email") String email);

}
