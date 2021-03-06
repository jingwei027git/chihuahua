package com.softpower.chihuahua.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.softpower.chihuahua.condition.RbUserCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.RbUser;
import com.softpower.chihuahua.service.RbUserService;
import com.softpower.chihuahua.test.GenericTest;
import com.softpower.chihuahua.test.util.TestUtil;

@Ignore
public class RbUserControllerTest extends GenericTest {

	private MockMvc mockMvc;

	@Mock
    private RbUserService rbUserServiceMock;

	@InjectMocks
    private RbUserController userController;


	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	private RbUser genMockUser1() {
		return genMockUsers(1).get(0);
	}

	private RbUser genMockUser10() {
		return genMockUsers(10).get(9);
	}

	private List<RbUser> genMockUsers(int count) {
		List<RbUser> users = new ArrayList<>();
		final String password = new BCryptPasswordEncoder().encode("softpower");
		for (int i = 0; i < count; i++) {
			RbUser user = new RbUser().init("TEST");
			user.setId(Long.valueOf(i + 1));
			user.setSysStatus(YesNo.Y);
			user.setUsername(String.format("%03d", i + 1));
			user.setFirstname(user.getUsername() + " firstname");
			user.setLastname(user.getUsername() + " lastname");
			user.setEmail(user.getFirstname() + "@softpower.com.tw");
			user.setPassword(password);
			user.setLockStatus(YesNo.N);
			user.setLoginCount(0);
			user.setErrorCount(0);
			user.setContinueErrorCount(0);
			user.setLastChangePasswordTime(DateTime.now());
			user.setExpireTime(DateTime.parse("2016-01-01"));
			users.add(user);
		}

		return users;
	}

	@Test
	public void getUsersTest() throws Exception {
		RbUserCond condition = new RbUserCond();
		when(rbUserServiceMock.list(RbWrapperDto.of(condition, getPrincipal()), Pagination.ALL)).thenReturn(genMockUsers(10));

		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

		verify(rbUserServiceMock, times(1)).list(RbWrapperDto.of(condition, getPrincipal()), Pagination.ALL);
		verifyNoMoreInteractions(rbUserServiceMock);
	}

	@Test
	public void getUser1Test() throws Exception {
		when(rbUserServiceMock.load(RbWrapperDto.of(1L, getPrincipal()))).thenReturn(genMockUser1());

		mockMvc.perform(get("/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

		verify(rbUserServiceMock, times(1)).load(RbWrapperDto.of(1L, getPrincipal()));
	}

	@Test
	public void getUser1Test404() throws Exception {
		when(rbUserServiceMock.load(RbWrapperDto.of(1L, getPrincipal()))).thenReturn(null);

		mockMvc.perform(get("/users/1"))
				.andExpect(status().is4xxClientError());

		verify(rbUserServiceMock, times(1)).load(RbWrapperDto.of(1L, getPrincipal()));
	}

	@Test
	public void postUserTest() throws Exception {
		RbUser user = genMockUser10();
		user.setId(null);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
//		String json = mapper.writeValueAsString(user);

//		when(rbUserServiceMock.create(any())).thenAnswer(new Answer<Object>() {
//			@Override
//			public Object answer(InvocationOnMock invocation) throws Throwable {
//				rbUserService.create(user);
//				return null;
//			}
//		});

//		mockMvc.perform(post("/users").contentType(TestUtil.APPLICATION_JSON_UTF8).content(json))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));
	}

}
