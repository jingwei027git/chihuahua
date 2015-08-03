package com.softpower.chihuahua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.RbRole;

@Component("RbRoleDao")
public interface RbRoleDao extends RbEntityDao<RbRole, Long> {

	public List<RbRole> findByUserId(@Param("userId") Long userId);

	public List<RbRole> findByRoleId(@Param("roleId") Long roleId);

	public RbRole findByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);

}
