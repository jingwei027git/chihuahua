package com.softpower.chihuahua.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.JslogApp;

@Component("JslogAppDao")
public interface JslogAppDao extends RbEntityDao<JslogApp, Long> {
	
	public JslogApp findByAppKey(@Param("appKey") String appKey);

}
