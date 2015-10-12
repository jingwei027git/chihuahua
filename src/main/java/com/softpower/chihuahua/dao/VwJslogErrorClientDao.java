package com.softpower.chihuahua.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.condition.VwJslogErrorClientCond;
import com.softpower.chihuahua.core.dao.RbEntityViewDao;
import com.softpower.chihuahua.view.VwJslogErrorClient;

@Component("VwJslogErrorClientDao")
public interface VwJslogErrorClientDao extends RbEntityViewDao<VwJslogErrorClient, Long> {

	public List<VwJslogErrorClient> findAll(
		@Param("cond") VwJslogErrorClientCond cond);

}
