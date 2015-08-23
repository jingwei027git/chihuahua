package com.softpower.chihuahua.service.impl;

import static com.softpower.chihuahua.core.util.string.RbStrings.begline;
import static com.softpower.chihuahua.core.util.string.RbStrings.newline;

import javax.annotation.Resource;

import lombok.Getter;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.softpower.chihuahua.core.dto.RbWrapperDto;
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

@Getter
@Component("JslogOnErrorService")
public class JslogOnErrorServiceImpl
	extends RbModelServiceImpl<JslogOnErrorModel, JslogOnErrorModel>
	implements JslogOnErrorService
{
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
	public long create(RbWrapperDto<JslogOnErrorModel> wrapperDto) {
		final JslogOnErrorModel model = wrapperDto.getModel();
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

		error.init(wrapperDto.getUsername());
		jslogErrorDao.save(error);

		return error.getId();
	}

	@Override
	public int update(RbWrapperDto<JslogOnErrorModel> wrapperDto) {
		final JslogOnErrorModel model = wrapperDto.getModel();
		JslogError jslogError = jslogErrorDao.findOne(model.getError().getId());

		if (model.getScreenshot() != null) {
			JslogScreenshot screenshot = model.getScreenshot();
			jslogScreenshotDao.save(screenshot);
			return jslogErrorDao.updateScreenshotIdById(jslogError.getId(), screenshot.getId(), wrapperDto.getUsername(), DateTime.now());
		}

		if (model.getSourcecode() != null) {
			JslogSourcecode sourcecode = model.getSourcecode();
			jslogSourcecodeDao.save(sourcecode);
			return jslogErrorDao.updateSourcecodeIdById(jslogError.getId(), sourcecode.getId(), wrapperDto.getUsername(), DateTime.now());
		}

		return 0;
	}

	@Override
	public String generateScriptCodeByAppId(Long appId, String url, boolean screenshot, boolean sourcecode) {
		final JslogApp app = jslogAppDao.findOne(appId);
		String scriptCode = RbStrings.concats(
			begline(), "<script type=\"text/javascript\" src=\"" + url + "/assets/softpower/jslogOnError.js\"></script>",
			newline(), "<script type=\"text/javascript\">",
			newline(), "<!--",
			newline(), "var jslog_params = jslog_params || [];",
			newline(), "jslog_params.push(\"" + app.getAppKey() + "\");",
			newline(), "jslog_params.push(\"" + url + "/errors\");",
			newline(), "var jslog_opts = jslog_opts || {};",
			newline(), "jslog_opts.screenshot = " + screenshot + ";",
			newline(), "jslog_opts.sourcecode = " + sourcecode + ";",
			newline(), "errors = [];",
			newline(), "window.onerror = function() {",
			newline(), "errors.push(arguments);",
			newline(), "JSLOG_ONERROR.collect();",
			newline(), "}",
			newline(), "//-->",
			newline(), "</script>"
		);

		return scriptCode;
	}

}
