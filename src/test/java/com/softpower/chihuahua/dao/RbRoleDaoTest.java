package com.softpower.chihuahua.dao;

import java.util.List;

import javax.annotation.Resource;

import com.google.common.collect.Iterables;
import com.softpower.chihuahua.core.enums.YesNo;
import com.softpower.chihuahua.entity.RbRole;
import com.softpower.chihuahua.test.GenericTest;

import org.junit.Assert;
import org.junit.Test;

public class RbRoleDaoTest extends GenericTest {

	@Resource(name = "RbRoleDao")
	private RbRoleDao rbRoleDao;

	@Test
	public void testFindByUserId() {
		List<RbRole> roles = rbRoleDao.findByUserId(1L);
		Assert.assertEquals(2, Iterables.size(roles));
	}

	@Test
	public void testFindByRoleId() {
		List<RbRole> roles = rbRoleDao.findByRoleId(1L);
		Assert.assertEquals(1, Iterables.size(roles));
	}

	@Test
	public void testSave() {
		RbRole role = new RbRole().init("TESTSAVE");
		role.setSysStatus(YesNo.Y);
		role.setCode("ROLE_TEST");
		int count = rbRoleDao.save(role);
		Assert.assertEquals(1, count);
	}

	@Test
	public void testUpdate() {
		RbRole role = rbRoleDao.findOne(1L);
		role.setName("TESTSAVE NAME");
		int count = rbRoleDao.update(role);
		Assert.assertEquals(1, count);

		role = rbRoleDao.findOne(1L);
		Assert.assertEquals("TESTSAVE NAME", role.getName());
	}

	@Test
	public void testDelete() {
		RbRole role = rbRoleDao.findOne(1L);
		Assert.assertNotNull(role);

		int count = rbRoleDao.delete(role);
		Assert.assertEquals(1, count);

		role = rbRoleDao.findOne(1L);
		Assert.assertNull(role);
	}

}
