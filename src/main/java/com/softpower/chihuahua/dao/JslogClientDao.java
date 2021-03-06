package com.softpower.chihuahua.dao;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.JslogClient;

@Component("JslogClientDao")
public interface JslogClientDao extends RbEntityDao<JslogClient, Long> {

}
