package com.softpower.chihuahua.datamodel;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.entity.JslogClient;
import com.softpower.chihuahua.entity.JslogError;
import com.softpower.chihuahua.entity.JslogScreenshot;
import com.softpower.chihuahua.entity.JslogSourcecode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class JslogOnErrorModel implements RbModel, RbCond {

	private JslogError error;
	private JslogClient client;
	private JslogScreenshot screenshot;
	private JslogSourcecode sourcecode;

	private String appKey; 	// [Ex] 3d9a24a783494c0b9ef6eb88a811a4a5 (to find out JslogApp)
	
}
