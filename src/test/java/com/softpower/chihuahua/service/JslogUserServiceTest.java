package com.softpower.chihuahua.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.collect.Iterables;
import com.softpower.chihuahua.condition.JslogUserCond;
import com.softpower.chihuahua.core.enums.SortOption;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.core.pagination.OrderBy;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.JslogUser;
import com.softpower.chihuahua.test.GenericTest;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class JslogUserServiceTest extends GenericTest {

	@Resource(name = "JslogUserService")
	private JslogUserService jslogUserService;

	private void createDummyUser(final String namePrefix, int count) {
		final String password = new BCryptPasswordEncoder().encode("softpower");
		for (int i = 0; i < count; i++) {
			JslogUser user = new JslogUser().init("TEST");
			user.setSysStatus(YesNo.Y);
			user.setUsername(namePrefix + String.format("%03d", i + 1));
			user.setFullname(user.getUsername() + " name");
			user.setEmail(user.getFullname() + "@softpower.com.tw");
			user.setPassword(password);
			jslogUserService.create(user);
		}
	}

	@Test
	public void testFindByCondition() {
		// create dummy users
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		// test page 1, size 10, orderBy ID asc
		JslogUserCond cond = new JslogUserCond();
		cond.setFullname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(1);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		Iterable<JslogUser> users = jslogUserService.list(cond, page);
		Assert.assertEquals(10, Iterables.size(users));
		Assert.assertEquals(namePrefix + "001", Iterables.getFirst(users, null).getFullname());
		Assert.assertEquals(namePrefix + "010", Iterables.getLast(users, null).getFullname());
	}

	@Test
	public void testFindByConditionPage2() {
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		// test page 1, size 10, orderBy ID asc
		JslogUserCond cond = new JslogUserCond();
		cond.setFullname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(2);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		Iterable<JslogUser> users = jslogUserService.list(cond, page);
		Assert.assertEquals(10, Iterables.size(users));
		Assert.assertEquals(namePrefix + "011", Iterables.getFirst(users, null).getFullname());
		Assert.assertEquals(namePrefix + "020", Iterables.getLast(users, null).getFullname());
	}

	@Test
	public void testFindByConditionPage3() {
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		JslogUserCond cond = new JslogUserCond();
		cond.setFullname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(3);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		Iterable<JslogUser> users = jslogUserService.list(cond, page);
		Assert.assertEquals(6, Iterables.size(users));
		Assert.assertEquals(namePrefix + "021", Iterables.getFirst(users, null).getFullname());
		Assert.assertEquals(namePrefix + "026", Iterables.getLast(users, null).getFullname());
	}

	@Test
	public void testFindByConditionPage2WithOrder() {
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		JslogUserCond cond = new JslogUserCond();
		cond.setFullname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(2);
		page.setSize(10);
		page.setOrderBy(OrderBy.create("name", SortOption.DESC));

		Iterable<JslogUser> users = jslogUserService.list(cond, page);
		Assert.assertEquals(10, Iterables.size(users));
		Assert.assertEquals(namePrefix + "016", Iterables.getFirst(users, null).getFullname());
		Assert.assertEquals(namePrefix + "007", Iterables.getLast(users, null).getFullname());
	}

	@Test
	public void testFindByName() {
		List<JslogUser> users = jslogUserService.getByUsername("softpower");
		Assert.assertEquals(1, Iterables.size(users));
	}

	@Test
	public void testToStringExclude() {
		JslogUser user = jslogUserService.load(1L);
		Assert.assertNotNull(user);
		Assert.assertTrue(user.toString().indexOf("password") == -1);
	}

	@Test
	public void testDelete() {
		List<JslogUser> users = jslogUserService.getByUsername("test");
		for (JslogUser user : users) {
			jslogUserService.delete(user);
		}
	}

	@Test
	public void testSave() {
		JslogUser user = new JslogUser().init("TESTSAVE");
		user.setSysStatus(YesNo.Y);
		user.setUsername("testSave");
		user.setFullname("testSave name");
		user.setPassword(new BCryptPasswordEncoder().encode("softpower"));
		user.setEmail("testsave@softpower.com.tw");
		long id = jslogUserService.create(user);
		Assert.assertNotNull(id);
	}

}
