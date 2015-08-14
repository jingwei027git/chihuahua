package com.softpower.chihuahua.dao;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.JslogSourcecode;

@Component("JslogSourcecodeDao")
public interface JslogSourcecodeDao extends RbEntityDao<JslogSourcecode, Long> {

}
