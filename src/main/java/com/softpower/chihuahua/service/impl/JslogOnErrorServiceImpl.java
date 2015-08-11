package com.softpower.chihuahua.service.impl;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.service.RbModelServiceImpl;
import com.softpower.chihuahua.dao.JslogAppDao;
import com.softpower.chihuahua.dao.JslogClientDao;
import com.softpower.chihuahua.dao.JslogErrorDao;
import com.softpower.chihuahua.dao.JslogScreenshotDao;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;
import com.softpower.chihuahua.entity.JslogApp;
import com.softpower.chihuahua.entity.JslogClient;
import com.softpower.chihuahua.entity.JslogError;
import com.softpower.chihuahua.entity.JslogScreenshot;
import com.softpower.chihuahua.entity.RbUser;
import com.softpower.chihuahua.service.JslogOnErrorService;

import lombok.Getter;

@Getter
@Component("JslogOnErrorService")
@SuppressWarnings("serial")
public class JslogOnErrorServiceImpl extends RbModelServiceImpl<JslogOnErrorModel, JslogOnErrorModel> implements JslogOnErrorService {
	
	@Resource(name = "JslogAppDao")
	private JslogAppDao jslogAppDao;
	
	@Resource(name = "JslogClientDao")
	private JslogClientDao jslogClientDao;
	
	@Resource(name = "JslogScreenshotDao")
	private JslogScreenshotDao jslogScreenshotDao;
	
	@Resource(name = "JslogErrorDao")
	private JslogErrorDao jslogErrorDao;

	
	@Override
	public int create(JslogOnErrorModel model, RbUser user) {
		JslogError error = model.getError();
		JslogClient client = model.getClient();
		JslogScreenshot screenshot = model.getScreenshot();
		
		JslogApp app = jslogAppDao.findByAppKey(model.getAppKey());
		error.setAppId(app.getId());
		
		jslogClientDao.save(client);
		error.setClientId(client.getId());
		
		if (Objects.nonNull(screenshot)) {
			jslogScreenshotDao.save(screenshot);
			error.setScreenshotId(screenshot.getId());
		}
		
		error.init(user.getUsername());
		jslogErrorDao.save(error);
		
		return 1;
	}

}
