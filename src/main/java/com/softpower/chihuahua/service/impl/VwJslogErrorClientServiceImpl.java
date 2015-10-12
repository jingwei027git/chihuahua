package com.softpower.chihuahua.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.Getter;

import org.springframework.stereotype.Component;

import com.softpower.chihuahua.condition.VwJslogErrorClientCond;
import com.softpower.chihuahua.core.service.RbModelServiceImpl;
import com.softpower.chihuahua.dao.VwJslogErrorClientDao;
import com.softpower.chihuahua.service.VwJslogErrorClientService;
import com.softpower.chihuahua.view.VwJslogErrorClient;

@Getter
@Component("VwJslogErrorClientService")
public class VwJslogErrorClientServiceImpl
	extends RbModelServiceImpl<VwJslogErrorClient, VwJslogErrorClientCond>
	implements VwJslogErrorClientService
{
	@Resource(name = "VwJslogErrorClientDao")
	private VwJslogErrorClientDao vwJslogErrorClientDao;


	@Override
	public List<VwJslogErrorClient> list(VwJslogErrorClientCond cond) {
		return vwJslogErrorClientDao.findAll(cond);
	}

}
