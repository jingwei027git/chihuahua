package com.softpower.chihuahua.condition;

import lombok.Getter;
import lombok.Setter;

import org.joda.time.DateTime;

import com.softpower.chihuahua.core.dto.RbCond;

@Getter
@Setter
@SuppressWarnings("serial")
public class VwJslogErrorClientCond implements RbCond {

	private DateTime begCreateTime;
	private DateTime endCreateTime;
	private String errMsgLike;
	private String locationHrefLike;

}
