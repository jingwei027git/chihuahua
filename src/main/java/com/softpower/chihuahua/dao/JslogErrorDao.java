package com.softpower.chihuahua.dao;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.JslogError;

@Component("JslogErrorDao")
public interface JslogErrorDao extends RbEntityDao<JslogError, Long> {

}
