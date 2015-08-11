package com.softpower.chihuahua.service;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;
import com.softpower.chihuahua.test.GenericTest;

import org.junit.Test;

import lombok.SneakyThrows;

public class JslogOnErrorServiceTest extends GenericTest {

	@Resource(name = "JslogOnErrorService")
	private JslogOnErrorService jslogOnErrorService;
	
	
	@SneakyThrows
	private JslogOnErrorModel getModel() {
		final String json = "{\"appKey\":\"3d9a24a783494c0b9ef6eb88a811a4a5\",\"error\":{\"errMsg\":\"Uncaught SyntaxError: Unexpected identifier\",\"url\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"line\":144,\"col\":16,\"errObj\":\"\",\"errCount\":1},\"client\":{\"navigatorAppVersion\":\"5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorCookieEnabled\":true,\"navigatorLanguage\":\"zh-TW\",\"navigatorPlatform\":\"Win32\",\"navigatorUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorVendor\":\"Google Inc.\",\"screenWidth\":1920,\"screenHeight\":1080,\"screenAvailWidth\":1920,\"screenAvailHeight\":1040,\"screenColorDepth\":24,\"screenPixelDepth\":24,\"locationHref\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action#08/11/2015\",\"locationHostname\":\"localhost\",\"locationPathname\":\"/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"locationProtocol\":\"http:\",\"documentWidth\":1169,\"documentHeight\":5615},\"screenshot\":null}";
		ObjectMapper mapper = new ObjectMapper();
		JslogOnErrorModel model = mapper.readValue(json, JslogOnErrorModel.class);
		return model;
	}
	
	@Test
	public void testOnErrorSave() {
		JslogOnErrorModel model = getModel();
		model.getError().init("TEST");
		jslogOnErrorService.create(model, getPrincipalUser());
	}

}
