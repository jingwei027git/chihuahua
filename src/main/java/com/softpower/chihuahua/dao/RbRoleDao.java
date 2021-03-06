package com.softpower.chihuahua.dao;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.entity.RbRole;

@Component("RbRoleDao")
public interface RbRoleDao extends RbEntityDao<RbRole, Long> {

	public List<RbRole> findByUserIdAndSysStatus(
		@Param("userId") @Nonnull Long userId,
		@Param("sysStatus") YesNo sysStatus);

	public List<RbRole> findByRoleIdAndSysStatus(
		@Param("roleId") @Nonnull Long roleId,
		@Param("sysStatus") YesNo sysStatus);

	public RbRole findByUserIdAndRoleIdAndSysStatus(
		@Param("userId") @Nonnull Long userId,
		@Param("roleId") @Nonnull Long roleId,
		@Param("sysStatus") YesNo sysStatus);

}
