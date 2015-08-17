package com.softpower.chihuahua.dao;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.entity.RbOperation;

@Component("RbOperationDao")
public interface RbOperationDao extends RbEntityDao<RbOperation, Long> {

	public List<RbOperation> findByUserIdAndSysStatus(
		@Param("userId") @Nonnull Long userId,
		@Param("sysStatus") YesNo sysStatus);

	public List<RbOperation> findByRoleIdAndSysStatus(
		@Param("roleId") @Nonnull Long roleId,
		@Param("sysStatus") YesNo sysStatus);

	public RbOperation findByUserIdAndRoleIdAndSysStatus(
		@Param("userId") @Nonnull Long userId,
		@Param("roleId") @Nonnull Long roleId,
		@Param("sysStatus") YesNo sysStatus);

}
