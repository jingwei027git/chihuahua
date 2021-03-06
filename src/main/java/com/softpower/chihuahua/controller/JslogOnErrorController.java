package com.softpower.chihuahua.controller;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.softpower.chihuahua.condition.VwJslogErrorClientCond;
import com.softpower.chihuahua.core.controller.RbControllerBase;
import com.softpower.chihuahua.core.controller.delegate.RbModelRestControllerDelegate;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;
import com.softpower.chihuahua.service.JslogOnErrorService;
import com.softpower.chihuahua.service.VwJslogErrorClientService;
import com.softpower.chihuahua.view.VwJslogErrorClient;

@Slf4j
@RestController
@RequestMapping("/errors")
public class JslogOnErrorController extends RbControllerBase {

	@Resource(name = "JslogOnErrorService")
	private JslogOnErrorService jslogErrorService;

	@Resource(name = "VwJslogErrorClientService")
	private VwJslogErrorClientService vwJslogErrorClientService;

	private RbModelRestControllerDelegate<JslogOnErrorModel, JslogOnErrorModel> delegate;
	private RbModelRestControllerDelegate<VwJslogErrorClient, VwJslogErrorClientCond> vwDelegate;


	@Override
	public void init() {
		delegate = new RbModelRestControllerDelegate<>(jslogErrorService);
		vwDelegate = new RbModelRestControllerDelegate<>(vwJslogErrorClientService);
	}

	public HttpEntity<List<VwJslogErrorClient>> list(@RequestBody VwJslogErrorClientCond cond) {
		return vwDelegate.list(cond);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<JslogOnErrorModel> create(@RequestBody JslogOnErrorModel model) {
		return delegate.create(model);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public HttpEntity<Void> update(@RequestBody JslogOnErrorModel model) {
		return delegate.update(model);
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

		final String reqUrl = getRequest().getRequestURL().toString();
		final String url = reqUrl.substring(0, reqUrl.indexOf("/errors"));
        String scriptCode = jslogErrorService.generateScriptCodeByAppId(appId, url, screenshot, sourcecode);

    	return new ResponseEntity<>(scriptCode, HttpStatus.OK);
	}

}
