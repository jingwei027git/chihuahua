package com.softpower.chihuahua.dao;

import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.JslogError;

@Component("JslogErrorDao")
public interface JslogErrorDao extends RbEntityDao<JslogError, Long> {
	
	public int updateScreenshotIdById(
		@Param("id") Long id,
		@Param("screenshotId") Long screenshotId,
		@Param("modifyUser") String modifyUser,
		@Param("modifyTime") DateTime modifyTime);
	
	public int updateSourcecodeIdById(
		@Param("id") Long id,
		@Param("sourcecodeId") Long sourcecodeId,
		@Param("modifyUser") String modifyUser,
		@Param("modifyTime") DateTime modifyTime);
	
}
