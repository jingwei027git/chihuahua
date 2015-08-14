package com.softpower.chihuahua.entity;

import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
public class JslogError extends RbEntityLogTimeBase {

	private Long appId;
	private Long clientId;
	private Long screenshotId;
	private Long sourcecodeId;

	private String errMsg;		// [Ex] Uncaught SyntaxError: Unexpected identifier
	private String url;			// [Ex] http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action
	private Integer line;		// [Ex] 144
	private Integer col;		// [Ex] 16
	private Integer errCount;	// [Ex] 1
	private String errObj;		// [Ex] N/A

}
