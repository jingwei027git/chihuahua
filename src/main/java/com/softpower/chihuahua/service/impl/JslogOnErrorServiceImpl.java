package com.softpower.chihuahua.service.impl;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.service.RbModelServiceImpl;
import com.softpower.chihuahua.core.util.string.RbStrings;
import com.softpower.chihuahua.dao.JslogAppDao;
import com.softpower.chihuahua.dao.JslogClientDao;
import com.softpower.chihuahua.dao.JslogErrorDao;
import com.softpower.chihuahua.dao.JslogScreenshotDao;
import com.softpower.chihuahua.dao.JslogSourcecodeDao;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;
import com.softpower.chihuahua.entity.JslogApp;
import com.softpower.chihuahua.entity.JslogClient;
import com.softpower.chihuahua.entity.JslogError;
import com.softpower.chihuahua.entity.JslogScreenshot;
import com.softpower.chihuahua.entity.JslogSourcecode;
import com.softpower.chihuahua.service.JslogOnErrorService;

import lombok.Getter;

@Getter
@Component("JslogOnErrorService")
@SuppressWarnings("serial")
public class JslogOnErrorServiceImpl extends RbModelServiceImpl<JslogOnErrorModel, JslogOnErrorModel> implements JslogOnErrorService {
	
	@Resource(name = "JslogAppDao")
	private JslogAppDao jslogAppDao;
	
	@Resource(name = "JslogErrorDao")
	private JslogErrorDao jslogErrorDao;

	@Resource(name = "JslogClientDao")
	private JslogClientDao jslogClientDao;
	
	@Resource(name = "JslogScreenshotDao")
	private JslogScreenshotDao jslogScreenshotDao;
	
	@Resource(name = "JslogSourcecodeDao")
	private JslogSourcecodeDao jslogSourcecodeDao;
	
	
	@Override
	public long create(JslogOnErrorModel model, UserDetails user) {
		JslogError error = model.getError();
		JslogClient client = model.getClient();
		JslogScreenshot screenshot = model.getScreenshot();
		
		JslogApp app = jslogAppDao.findByAppKey(model.getAppKey());
		error.setAppId(app.getId());
		
		jslogClientDao.save(client);
		error.setClientId(client.getId());
		
		if (screenshot != null) {
			jslogScreenshotDao.save(screenshot);
			error.setScreenshotId(screenshot.getId());
		}
		
		error.init(user.getUsername());
		jslogErrorDao.save(error);
		
		return error.getId();
	}
	
	@Override
	public int update(JslogOnErrorModel model, UserDetails user) {
		JslogError jslogError = jslogErrorDao.findOne(model.getError().getId());
		
		if (model.getScreenshot() != null) {
			JslogScreenshot screenshot = model.getScreenshot();
			jslogScreenshotDao.save(screenshot);
			return jslogErrorDao.updateScreenshotIdById(jslogError.getId(), screenshot.getId(), user.getUsername(), DateTime.now());
		}
		
		if (model.getSourcecode() != null) {
			JslogSourcecode sourcecode = model.getSourcecode();
			jslogSourcecodeDao.save(sourcecode);
			return jslogErrorDao.updateSourcecodeIdById(jslogError.getId(), sourcecode.getId(), user.getUsername(), DateTime.now());
		}
		
		return 0;
	}
	
	@Override
	public String generateScriptCodeByAppId(Long appId, String url, boolean screenshot, boolean sourcecode) {
		final JslogApp app = jslogAppDao.findOne(appId);
		String scriptCode = RbStrings.concats(
			"<script type=\"text/javascript\" src=\"" + url + "/js/jslogOnError.js\"></script>", "\n",
			"<script type=\"text/javascript\">", "\n",
			"<!--", "\n",
			"var jslog_params = jslog_params || [];", "\n",
			"jslog_params.push(\"" + app.getAppKey() + "\");", "\n",
			"jslog_params.push(\"" + url + "/errors\");", "\n",
			"var jslog_opts = jslog_opts || {};", "\n",
			"jslog_opts.screenshot = " + screenshot + ";", "\n",
			"jslog_opts.sourcecode = " + sourcecode + ";", "\n",
			"errors = [];", "\n",
			"window.onerror = function() {",
			"errors.push(arguments);",
			"JSLOG_ONERROR.collect();",
			"}", "\n",
			"//-->", "\n",
			"</script>"
		);
		
		return scriptCode;
	}

}
