package com.softpower.chihuahua.dao;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.collect.Iterables;
import com.softpower.chihuahua.condition.RbUserCond;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.core.pagination.OrderBy;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.RbUser;
import com.softpower.chihuahua.test.GenericTest;

public class RbUserDaoTest extends GenericTest {

	@Resource(name = "RbUserDao")
	private RbUserDao rbUserDao;

	@Test
	public void testFindOne() {
		RbUser user = rbUserDao.findOne(1L);
		Assert.assertNotNull(user);
	}

	@Test
	public void testFindAll() {
		RbUserCond cond = new RbUserCond();

		Pagination page = new Pagination();
		page.setPage(1);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		List<RbUser> users = rbUserDao.findAll(cond, page);
		Assert.assertEquals(2, Iterables.size(users));
	}

	@Test
	public void testCount() {
		RbUserCond cond = new RbUserCond();
		cond.setLockStatus(YesNo.N);

		long count = rbUserDao.count(cond);
		Assert.assertEquals(2, count);
	}

	@Test
	public void testFindByUsername() {
		RbUser user = rbUserDao.findByUsername("admin");
		Assert.assertNotNull(user);
	}

	@Test
	public void testFindByEmail() {
		RbUser user = rbUserDao.findByEmail("admin@softpower.com.tw");
		Assert.assertNotNull(user);
	}

	@Test
	public void testSave() {
		RbUser user = RbUser.createEntity(RbUser.class, "TESTSAVE");
		user.setSysStatus(YesNo.Y);
		user.setUsername("testSave");
		user.setFirstname("first name");
		user.setLastname("first name");
		user.setEmail("testsave@softpower.com.tw");
		user.setDescription("description");
		user.setLockStatus(YesNo.N);
		user.setPassword(new BCryptPasswordEncoder().encode("softpower"));
		user.setLoginCount(0);
		user.setErrorCount(0);
		user.setContinueErrorCount(0);
		user.setLastChangePasswordTime(DateTime.now());
		user.setFirstLoginTime(null);
		user.setLastLoginTime(null);
		user.setExpireTime(DateTime.parse("2016-01-01"));
		int count = rbUserDao.save(user);
		Assert.assertEquals(1, count);
	}

	@Test
	public void testUpdate() {
		RbUser user = rbUserDao.findOne(1L);
		user.setEmail("testUpdate@softpower.com.tw");
		int count = rbUserDao.update(user);
		Assert.assertEquals(1, count);

		user = rbUserDao.findOne(1L);
		Assert.assertEquals("testUpdate@softpower.com.tw", user.getEmail());
	}

	@Test
	public void testDelete() {
		RbUser user = rbUserDao.findOne(1L);
		user.setEmail("testUpdate@softpower.com.tw");
		int count = rbUserDao.update(user);
		Assert.assertEquals(1, count);

		count = rbUserDao.delete(user);
		Assert.assertEquals(1, count);

		user = rbUserDao.findOne(1L);
		Assert.assertNull(user);
	}

}
