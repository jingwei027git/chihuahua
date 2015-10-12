package com.softpower.chihuahua.dao;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;

import com.google.common.collect.Iterables;
import com.softpower.chihuahua.condition.VwJslogErrorClientCond;
import com.softpower.chihuahua.core.pagination.OrderBy;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.test.GenericTest;
import com.softpower.chihuahua.view.VwJslogErrorClient;

public class VwJslogErrorClientDaoTest extends GenericTest {

	@Resource(name = "VwJslogErrorClientDao")
	private VwJslogErrorClientDao vwJslogErrorClientDao;

	@Test
	public void testFindOne() {
		VwJslogErrorClient vw = vwJslogErrorClientDao.findOne(50L);
		Assert.assertNotNull(vw);
	}

	@Test
	public void testFindAll() {
		VwJslogErrorClientCond cond = new VwJslogErrorClientCond();
		cond.setBegCreateTime(DateTime.parse("2015-09-10"));
		cond.setEndCreateTime(DateTime.parse("2015-09-11"));

		Pagination page = new Pagination();
		page.setPage(1);
		page.setSize(10);
		page.setOrderBy(OrderBy.DEFAULT);

		List<VwJslogErrorClient> cols = vwJslogErrorClientDao.findAll(cond, page);
		Assert.assertEquals(10, Iterables.size(cols));
	}

}
