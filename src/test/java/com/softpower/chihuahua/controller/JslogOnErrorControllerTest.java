package com.softpower.chihuahua.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.datamodel.JslogOnErrorModel;
import com.softpower.chihuahua.service.JslogOnErrorService;
import com.softpower.chihuahua.test.GenericTest;
import com.softpower.chihuahua.test.util.TestUtil;

import org.junit.Before;
import org.junit.Test;

public class JslogOnErrorControllerTest extends GenericTest {

	private MockMvc mockMvc;
	
	@Mock
	private JslogOnErrorService service;

	@InjectMocks
    private JslogOnErrorController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		Authentication auth = new UsernamePasswordAuthenticationToken(getPrincipal(),null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		controller.init();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCreate() throws Exception {
		final String jsonStr = "{\"appKey\":\"attraction\",\"error\":{\"errMsg\":\"Uncaught SyntaxError: Unexpected identifier\",\"url\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"line\":144,\"col\":16,\"errObj\":\"\",\"errCount\":1},\"client\":{\"navigatorAppVersion\":\"5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorCookieEnabled\":true,\"navigatorLanguage\":\"zh-TW\",\"navigatorPlatform\":\"Win32\",\"navigatorUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorVendor\":\"Google Inc.\",\"screenWidth\":1920,\"screenHeight\":1080,\"screenAvailWidth\":1920,\"screenAvailHeight\":1040,\"screenColorDepth\":24,\"screenPixelDepth\":24,\"locationHref\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action#08/11/2015\",\"locationHostname\":\"localhost\",\"locationPathname\":\"/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"locationProtocol\":\"http:\",\"documentWidth\":1169,\"documentHeight\":5615},\"screenshot\":null}";
		
//		RbWrapperDto<JslogOnErrorModel> wrapperDto = RbWrapperDto.of(new JslogOnErrorModel(), getPrincipal());
//		when(service.create(any(RbWrapperDto.class))).thenReturn(1L);
		
		mockMvc.perform(post("/errors")
			.contentType(TestUtil.APPLICATION_JSON_UTF8).content(jsonStr))
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8) );
	}
	
	@Test
	public void testUpdate() throws Exception {
		final String jsonStr = "{\"error\":{\"id\":20,\"createUser\":\"anonymousUser\",\"createTime\":1440067507905,\"modifyUser\":\"anonymousUser\",\"modifyTime\":1440067507905,\"appId\":1,\"clientId\":20,\"screenshotId\":null,\"sourcecodeId\":null,\"errMsg\":\"this is error message\",\"url\":\"http://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js\",\"line\":68,\"col\":-1,\"errCount\":2,\"errObj\":null},\"client\":{\"id\":20,\"navigatorAppVersion\":\"5.0 (Windows)\",\"navigatorCookieEnabled\":\"true\",\"navigatorLanguage\":\"zh-TW\",\"navigatorPlatform\":\"Win32\",\"navigatorUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0\",\"navigatorVendor\":\"\",\"screenWidth\":\"2144\",\"screenHeight\":\"1206\",\"screenAvailWidth\":\"2144\",\"screenAvailHeight\":\"1161\",\"screenColorDepth\":\"24\",\"screenPixelDepth\":\"24\",\"locationHref\":\"http://localhost:28080/chihuahua/welcome\",\"locationHostname\":\"localhost\",\"locationPathname\":\"/chihuahua/welcome\",\"locationProtocol\":\"http:\",\"documentWidth\":\"936\",\"documentHeight\":\"1027\"},\"screenshot\":null,\"sourcecode\":{\"mimetype\":\"html/text\",\"content\":\"this is sourcecode content\"},\"appKey\":\"3d9a24a783494c0b9ef6eb88a811a4a5\"}";
		
		mockMvc.perform(put("/errors/1")
			.contentType(TestUtil.APPLICATION_JSON_UTF8).content(jsonStr))
			.andExpect(status().isOk())
			.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8) );
	}

}
