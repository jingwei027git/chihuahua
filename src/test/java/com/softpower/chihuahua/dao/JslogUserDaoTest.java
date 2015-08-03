package com.softpower.chihuahua.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.collect.Iterables;
import com.softpower.chihuahua.condition.JslogUserCond;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.core.pagination.OrderBy;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.JslogUser;
import com.softpower.chihuahua.test.GenericTest;

public class JslogUserDaoTest extends GenericTest {

	@Resource(name = "JslogUserDao")
	private JslogUserDao jslogUserDao;

	@Test
	public void testFindOne() {
		JslogUser user = jslogUserDao.findOne(1L);
		Assert.assertNotNull(user);
	}

	@Test
	public void testFindAll() {
		JslogUserCond cond = new JslogUserCond();
		cond.setFullname("softpower");

		Pagination page = new Pagination();
		page.setPage(1);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		List<JslogUser> users = jslogUserDao.findAll(cond, page);
		Assert.assertEquals(1, Iterables.size(users));
	}

	@Test
	public void testCount() {
		JslogUserCond cond = new JslogUserCond();
		cond.setFullname("softpower");

		long count = jslogUserDao.count(cond);
		Assert.assertEquals(1, count);
	}

	@Test
	public void testSave() {
		JslogUser user = JslogUser.createEntity(JslogUser.class, "TESTSAVE");
		user.setSysStatus(YesNo.Y);
		user.setUsername("testSave");
		user.setFullname("testSave name");
		user.setPassword(new BCryptPasswordEncoder().encode("softpower"));
		user.setEmail("testsave@softpower.com.tw");
		int count = jslogUserDao.save(user);
		System.err.println(count);
	}

	@Test
	public void testUpdate() {
		JslogUser user = jslogUserDao.findOne(1L);
		user.setEmail("testUpdate@softpower.com.tw");
		int count = jslogUserDao.update(user);
		Assert.assertEquals(1, count);

		user = jslogUserDao.findOne(1L);
		Assert.assertEquals("testUpdate@softpower.com.tw", user.getEmail());
	}

	@Test
	public void testDelete() {
		JslogUser user = jslogUserDao.findOne(1L);
		user.setEmail("testUpdate@softpower.com.tw");
		int count = jslogUserDao.update(user);
		Assert.assertEquals(1, count);

		count = jslogUserDao.delete(user);
		Assert.assertEquals(1, count);

		user = jslogUserDao.findOne(1L);
		Assert.assertNull(user);
	}

}
