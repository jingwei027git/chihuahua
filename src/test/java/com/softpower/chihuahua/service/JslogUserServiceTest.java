package com.softpower.chihuahua.service;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.collect.Iterables;
import com.softpower.chihuahua.condition.JslogUserCond;
import com.softpower.chihuahua.core.entity.factory.RbEntityFactory;
import com.softpower.chihuahua.core.enums.SystemUser;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.core.pagination.OrderBy;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.JslogUser;
import com.softpower.chihuahua.test.GenericTest;

public class JslogUserServiceTest extends GenericTest {

	@Resource(name = "JslogUserService")
	private JslogUserService jslogUserService;

	@Test
	public void testFindByCondition() {
		JslogUserCond cond = new JslogUserCond();
		cond.setName("test");

		Pagination page = new Pagination();
		page.setPage(1);
		page.setSize(5);
		page.setOrderBy(OrderBy.DEFAULT);

		List<JslogUser> users = jslogUserService.list(cond, page);
		users.forEach((user) -> {
			System.err.println(user);
		});
	}

	@Test
	public void testFindByName() {
		List<JslogUser> users = jslogUserService.getByName("softpower");
		Assert.assertEquals(1, Iterables.size(users));
	}

	@Test
	public void testToStringExclude() {
		JslogUser user = jslogUserService.read(1L);
		Assert.assertNotNull(user);
		Assert.assertTrue(user.toString().indexOf("password") == -1);
	}

	@Test
	public void testDelete() {
		List<JslogUser> users = jslogUserService.getByName("test");
		for (JslogUser user : users) {
			jslogUserService.delete(user);
		}
	}

	@Test
	public void testSave() {
		JslogUser user = RbEntityFactory.createEntity(JslogUser.class, SystemUser.SYSTEM.toString(), DateTime.now());
		user.setSysStatus(YesNo.Y);
		user.setName("testSave");
		user.setPassword(new BCryptPasswordEncoder().encode("softpower"));
		user.setEmail("testsave@softpower.com.tw");
		int result = jslogUserService.create(user);
		Assert.assertEquals(1, result);
	}

}