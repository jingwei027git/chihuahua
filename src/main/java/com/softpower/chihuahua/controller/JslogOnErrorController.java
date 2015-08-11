package com.softpower.chihuahua.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softpower.chihuahua.core.controller.RbModelController;
import com.softpower.chihuahua.core.service.RbModelService;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;
import com.softpower.chihuahua.service.JslogOnErrorService;

@RestController
@RequestMapping("/errors")
public class JslogOnErrorController extends RbModelController<JslogOnErrorModel, JslogOnErrorModel> {
	
	@Resource(name = "JslogErrorService")
	private JslogOnErrorService jslogErrorService;
	
	@Override
	public RbModelService<JslogOnErrorModel, JslogOnErrorModel> getRbModelService() {
		return jslogErrorService;
	}

}
