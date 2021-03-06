package com.softpower.chihuahua.service;

import com.softpower.chihuahua.core.service.RbModelService;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;

public interface JslogOnErrorService
	extends RbModelService<JslogOnErrorModel, JslogOnErrorModel>
{
	public String generateScriptCodeByAppId(Long appId, String url, boolean screenshot, boolean sourcecode);

}
