package com.softpower.chihuahua.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.collect.Lists;
import com.softpower.chihuahua.condition.JslogUserCond;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.JslogUser;
import com.softpower.chihuahua.service.JslogUserService;
import com.softpower.chihuahua.test.GenericTest;
import com.softpower.chihuahua.test.util.TestUtil;

@WebAppConfiguration(value = "src/main/webapp")
public class JslogUserControllerTest extends GenericTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
    private JslogUserService serviceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		MockitoAnnotations.initMocks(this);
	}

	private List<JslogUser> genMockUsers(int count) {
		List<JslogUser> users = Lists.newArrayList();
		for (int i=0; i<count ; i++) {
			JslogUser user = JslogUser.createEntity(JslogUser.class, "test");
			user.setFullname("test" + String.format("%03d", i));
			user.setEmail(user.getFullname() + "@softpower.com.tw");
			users.add(user);
		}

		return users;
	}

	@Test
	public void findAll() throws Exception {
		JslogUserCond condition = new JslogUserCond();
//		when(serviceMock.list(condition, Pagination.ALL)).thenReturn(genMockUsers(10));

		mockMvc.perform(get("/russianblue/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

		verify(serviceMock, times(1)).list(condition, Pagination.ALL);
		verifyNoMoreInteractions(serviceMock);
	}
}
