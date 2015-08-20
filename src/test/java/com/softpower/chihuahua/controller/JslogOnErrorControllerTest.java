package com.softpower.chihuahua.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.softpower.chihuahua.core.controller.delegate.RbModelRestControllerDelegate;
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

	@Test
	public void testCreateError() throws Exception {
		final String jsonStr = "{\"appKey\":\"attraction\",\"error\":{\"errMsg\":\"Uncaught SyntaxError: Unexpected identifier\",\"url\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"line\":144,\"col\":16,\"errObj\":\"\",\"errCount\":1},\"client\":{\"navigatorAppVersion\":\"5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorCookieEnabled\":true,\"navigatorLanguage\":\"zh-TW\",\"navigatorPlatform\":\"Win32\",\"navigatorUserAgent\":\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.130 Safari/537.36\",\"navigatorVendor\":\"Google Inc.\",\"screenWidth\":1920,\"screenHeight\":1080,\"screenAvailWidth\":1920,\"screenAvailHeight\":1040,\"screenColorDepth\":24,\"screenPixelDepth\":24,\"locationHref\":\"http://localhost:18080/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action#08/11/2015\",\"locationHostname\":\"localhost\",\"locationPathname\":\"/AttractionSuite/ec/admin/reservation/initInventoryControlPanel.action\",\"locationProtocol\":\"http:\",\"documentWidth\":1169,\"documentHeight\":5615},\"screenshot\":null}";
		
		RbWrapperDto<JslogOnErrorModel> wrapperDto = RbWrapperDto.of(new JslogOnErrorModel(), getPrincipal());
		when(service.create(wrapperDto)).thenReturn(1L);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/errors").contentType(TestUtil.APPLICATION_JSON_UTF8).content(jsonStr))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

}
