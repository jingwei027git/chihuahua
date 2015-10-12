package com.softpower.chihuahua.service;

import java.util.List;

import com.softpower.chihuahua.condition.VwJslogErrorClientCond;
import com.softpower.chihuahua.core.service.RbModelService;
import com.softpower.chihuahua.view.VwJslogErrorClient;

public interface VwJslogErrorClientService
	extends RbModelService<VwJslogErrorClient, VwJslogErrorClientCond>
{

	public List<VwJslogErrorClient> list(VwJslogErrorClientCond cond);

}
