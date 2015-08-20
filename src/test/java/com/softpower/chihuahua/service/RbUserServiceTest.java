package com.softpower.chihuahua.service;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.collect.Iterables;
import com.softpower.chihuahua.condition.RbUserCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.enums.SortOption;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.core.pagination.OrderBy;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.RbUser;
import com.softpower.chihuahua.test.GenericTest;

import org.junit.Assert;
import org.junit.Test;

public class RbUserServiceTest extends GenericTest {

	@Resource(name = "RbUserService")
	private RbUserService rbUserService;

	private void createDummyUser(final String namePrefix, int count) {
		final String password = new BCryptPasswordEncoder().encode("softpower");
		for (int i = 0; i < count; i++) {
			RbUser user = new RbUser().init("TEST");
			user.setSysStatus(YesNo.Y);
			user.setUsername(namePrefix + String.format("%03d", i + 1));
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
			rbUserService.create(RbWrapperDto.of(user, getPrincipal()));
		}
	}

	@Test
	public void testFindAll() {
		// create dummy users
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		Iterable<RbUser> users = rbUserService.list(RbWrapperDto.of(new RbUserCond(), getPrincipal()), Pagination.ALL);
		Assert.assertTrue(Iterables.size(users) >= 26);
	}

	@Test
	public void testFindByCondition() {
		// create dummy users
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		// test page 1, size 10, orderBy ID asc
		RbUserCond cond = new RbUserCond();
		cond.setFirstname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(1);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		Iterable<RbUser> users = rbUserService.list(RbWrapperDto.of(cond, getPrincipal()), page);
		Assert.assertEquals(10, Iterables.size(users));
		Assert.assertEquals(namePrefix + "001", Iterables.getFirst(users, null).getUsername());
		Assert.assertEquals(namePrefix + "010", Iterables.getLast(users, null).getUsername());
	}

	@Test
	public void testFindByConditionPage2() {
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		// test page 1, size 10, orderBy ID asc
		RbUserCond cond = new RbUserCond();
		cond.setFirstname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(2);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		Iterable<RbUser> users = rbUserService.list(RbWrapperDto.of(cond, getPrincipal()), page);
		Assert.assertEquals(10, Iterables.size(users));
		Assert.assertEquals(namePrefix + "011", Iterables.getFirst(users, null).getUsername());
		Assert.assertEquals(namePrefix + "020", Iterables.getLast(users, null).getUsername());
	}

	@Test
	public void testFindByConditionPage3() {
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		RbUserCond cond = new RbUserCond();
		cond.setFirstname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(3);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		Iterable<RbUser> users = rbUserService.list(RbWrapperDto.of(cond, getPrincipal()), page);
		Assert.assertEquals(6, Iterables.size(users));
		Assert.assertEquals(namePrefix + "021", Iterables.getFirst(users, null).getUsername());
		Assert.assertEquals(namePrefix + "026", Iterables.getLast(users, null).getUsername());
	}

	@Test
	public void testFindByConditionPage2WithOrder() {
		final String namePrefix = "testFindByCondition";
		createDummyUser(namePrefix, 26);

		RbUserCond cond = new RbUserCond();
		cond.setFirstname("testFindByCondition");

		Pagination page = new Pagination();
		page.setPage(2);
		page.setSize(10);
		page.setOrderBy(OrderBy.create("username", SortOption.DESC));

		Iterable<RbUser> users = rbUserService.list(RbWrapperDto.of(cond, getPrincipal()), page);
		Assert.assertEquals(10, Iterables.size(users));
		Assert.assertEquals(namePrefix + "016", Iterables.getFirst(users, null).getUsername());
		Assert.assertEquals(namePrefix + "007", Iterables.getLast(users, null).getUsername());
	}

	@Test
	public void testFindByName() {
		RbUser user = rbUserService.getByUsername("admin");
		Assert.assertNotNull(user);
	}

	@Test
	public void testToStringExclude() {
		RbUser user = rbUserService.load(RbWrapperDto.of(1L, getPrincipal()));
		Assert.assertNotNull(user);
		Assert.assertTrue(user.toString().indexOf("password") == -1);
	}

	@Test
	public void testDelete() {
		RbUser user = rbUserService.getByUsername("admin");
		rbUserService.delete(RbWrapperDto.of(user, getPrincipal()));
	}

	@Test
	public void testSave() {
		RbUser user = new RbUser().init("TESTSAVE");
		user.setSysStatus(YesNo.Y);
		user.setUsername("testSave");
		user.setFirstname("testSave name");
		user.setPassword(new BCryptPasswordEncoder().encode("softpower"));
		user.setEmail("testsave@softpower.com.tw");
		user.setLockStatus(YesNo.N);
		user.setLoginCount(0);
		user.setErrorCount(0);
		user.setContinueErrorCount(0);
		user.setLastChangePasswordTime(DateTime.now());
		user.setExpireTime(DateTime.parse("2016-01-01"));
		long id = rbUserService.create(RbWrapperDto.of(user, getPrincipal()));
		Assert.assertNotNull(id);
	}

}
