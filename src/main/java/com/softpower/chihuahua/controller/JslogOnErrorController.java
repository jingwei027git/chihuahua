package com.softpower.chihuahua.controller;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.softpower.chihuahua.core.controller.RbModelController;
import com.softpower.chihuahua.core.service.RbModelService;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;
import com.softpower.chihuahua.service.JslogOnErrorService;

@Slf4j
@RestController
@RequestMapping("/errors")
public class JslogOnErrorController extends RbModelController<JslogOnErrorModel, JslogOnErrorModel> {

	@Resource(name = "JslogOnErrorService")
	private JslogOnErrorService jslogErrorService;

	@Override
	public RbModelService<JslogOnErrorModel, JslogOnErrorModel> getRbModelService() {
		return jslogErrorService;
	}

	@RequestMapping(value = "/scriptcode/{appId}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
	public HttpEntity<String> generateScriptCode(
		@PathVariable(value = "appId") Long appId,
		@RequestParam(value = "screenshot", required = false, defaultValue = "false") Boolean screenshot,
		@RequestParam(value = "sourcecode", required = false, defaultValue = "false") Boolean sourcecode)
	{
		log.info("readScriptCode id {}", appId);
		Preconditions.checkArgument(appId != null);

		final String reqUrl = request.getRequestURL().toString();
		final String url = reqUrl.substring(0, reqUrl.indexOf("/errors"));
        String scriptCode = jslogErrorService.generateScriptCodeByAppId(appId, url, screenshot, sourcecode);
    	return new ResponseEntity<>(scriptCode, HttpStatus.OK);
	}

}
