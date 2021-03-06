package com.softpower.chihuahua.dao;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.RbRole;

@Component("RbUserRoleDao")
public interface RbUserRoleDao extends RbEntityDao<RbRole, Long> {

}
