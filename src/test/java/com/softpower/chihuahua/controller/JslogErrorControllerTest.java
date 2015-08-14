package com.softpower.chihuahua.controller;

import javax.annotation.Resource;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.softpower.chihuahua.service.JslogOnErrorService;
import com.softpower.chihuahua.test.GenericTest;

import org.junit.Before;
import org.junit.Test;

public class JslogErrorControllerTest extends GenericTest {

	private MockMvc mockMvc;
	
	@Resource(name = "JslogOnErrorService")
	private JslogOnErrorService service;
	
	@Mock
	private JslogOnErrorService serviceMock;

	@InjectMocks
    private JslogOnErrorController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		Authentication auth = new UsernamePasswordAuthenticationToken(getPrincipalUser(),null);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	public void testCreateError() throws Exception {
		final String jsonStr = "{\"appKey\":\"attraction\",\"error\":{\"errMsg\":\"Uncaught SyntaxError: Unexpected identifier\",\"url\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"line\":144,\"col\":16,\"errObj\":\"\",\"errCount\":1},\"client\":{\"navigatorAppVersion\":\"5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorCookieEnabled\":true,\"navigatorLanguage\":\"zh-TW\",\"navigatorPlatform\":\"Win32\",\"navigatorUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorVendor\":\"Google Inc.\",\"screenWidth\":1920,\"screenHeight\":1080,\"screenAvailWidth\":1920,\"screenAvailHeight\":1040,\"screenColorDepth\":24,\"screenPixelDepth\":24,\"locationHref\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action#08/11/2015\",\"locationHostname\":\"localhost\",\"locationPathname\":\"/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"locationProtocol\":\"http:\",\"documentWidth\":1169,\"documentHeight\":5615},\"screenshot\":null}";
		
//		when(serviceMock.create(any(), any())).thenReturn(1L);
		
//		mockMvc.perform(post("/errors").contentType(TestUtil.APPLICATION_JSON_UTF8).content(jsonStr))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

}
