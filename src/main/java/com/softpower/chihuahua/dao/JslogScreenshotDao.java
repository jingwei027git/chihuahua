package com.softpower.chihuahua.dao;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.entity.JslogScreenshot;

@Component("JslogScreenshotDao")
public interface JslogScreenshotDao extends RbEntityDao<JslogScreenshot, Long> {

}
