package com.softpower.chihuahua.core.service;

import java.util.List;

import org.joda.time.DateTime;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;

@SuppressWarnings("serial")
public abstract class RbEntityServiceImpl<T extends RbEntity, C extends RbModel, DAO extends RbEntityDao<T, Long>> extends RbServiceImpl<T>
		implements RbEntityService<T, C, Long> {

	public abstract DAO getDao();

	@Override
	public T read(Long pk) {
		T entity = getDao().read(pk);
		return entity;
	}

	@Override
	public List<T> list(C condition, Pagination pagination) {
		return getDao().readAll(condition, pagination);
	}

	@Override
	public Integer create(T entity) {
		if (entity instanceof RbEntityLogTimeBase) {
			final RbEntityLogTimeBase e = (RbEntityLogTimeBase) entity;
			if (e.getCreateTime() == null) {
				e.setCreateTime(DateTime.now());
				e.setModifyTime(e.getCreateTime());
			}
		}

		int count = getDao().create(entity);
		return count;
	}

	@Override
	public Integer update(T entity) {
		if (entity instanceof RbEntityLogTimeBase) {
			final RbEntityLogTimeBase e = (RbEntityLogTimeBase) entity;
			if (e.getModifyTime() == null) {
				e.setModifyTime(DateTime.now());
			}
		}

		int count = getDao().update(entity);
		return count;
	}

	@Override
	public Integer delete(T entity) {
		int count = getDao().delete(entity);
		return count;
	}

	@Override
	public Integer delete(final Long pk) {
		T entity = read(pk);
		if (entity != null) {
			int count = getDao().delete(entity);
			return count;
		} else {
			return 0;
		}
	}

}
