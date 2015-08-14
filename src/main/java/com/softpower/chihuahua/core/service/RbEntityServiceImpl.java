package com.softpower.chihuahua.core.service;

import java.util.List;

import org.joda.time.DateTime;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;
import com.softpower.chihuahua.core.pagination.Pagination;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("serial")
public abstract class RbEntityServiceImpl<T extends RbEntity, C extends RbCond, DAO extends RbEntityDao<T, Long>>
		extends RbModelServiceImpl<T, C>
		implements RbEntityService<T, C, Long> {

	public abstract DAO getDao();

	@Override
	public T load(Long pk) {
		T entity = getDao().findOne(pk);
		return entity;
	}

	@Override
	public List<T> list(C condition, Pagination pagination) {
		return getDao().findAll(condition, pagination);
	}

	@Override
	public long create(T entity) {
		if (entity instanceof RbEntityLogTimeBase) {
			final RbEntityLogTimeBase e = (RbEntityLogTimeBase) entity;
			if (e.getCreateTime() == null) {
				e.setCreateTime(DateTime.now());
				e.setModifyTime(e.getCreateTime());
			}
		}

		int count = getDao().save(entity);
		log.debug("saveCount {}", count);
		return entity.getId();
	}

	@Override
	public int update(T entity) {
		if (entity instanceof RbEntityLogTimeBase) {
			final RbEntityLogTimeBase e = (RbEntityLogTimeBase) entity;
			if (e.getModifyTime() == null) {
				e.setModifyTime(DateTime.now());
			}
		}

		int count = getDao().update(entity);
		log.debug("updateCount {}", count);
		return count;
	}

	@Override
	public int delete(T entity) {
		int count = getDao().delete(entity);
		log.debug("deleteCount {}", count);
		return count;
	}

	@Override
	public int delete(final Long pk) {
		int count = 0;
		T entity = load(pk);
		if (entity != null) {
			count = getDao().delete(entity);
		} else {
			count = 0;
		}
		log.debug("deleteCount {}", count);
		return count;
	}

}
