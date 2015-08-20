package com.softpower.chihuahua.core.service;

import java.util.List;

import com.softpower.chihuahua.core.dao.RbEntityDao;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.entity.RbEntityLogTimeBase;
import com.softpower.chihuahua.core.pagination.Pagination;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RbEntityServiceImpl<T extends RbEntity, C extends RbCond, DAO extends RbEntityDao<T, Long>>
		implements RbEntityService<T, C, Long>
{
	protected abstract DAO getDao();

	@Override
	public T load(RbWrapperDto<Long> wrapperDto) {
		final Long pk = wrapperDto.getModel();
		T entity = getDao().findOne(pk);
		return entity;
	}

	@Override
	public List<T> list(RbWrapperDto<C> wrapperDto, Pagination pagination) {
		final C condition = wrapperDto.getModel();
		return getDao().findAll(condition, pagination);
	}

	@Override
	public long create(RbWrapperDto<T> wrapperDto) {
		final T entity = wrapperDto.getModel();
		if (entity instanceof RbEntityLogTimeBase) {
			((RbEntityLogTimeBase) entity).init(wrapperDto.getUsername());
		}

		int count = getDao().save(entity);
		log.debug("saveCount {}", count);
		return entity.getId();
	}

	@Override
	public int update(RbWrapperDto<T> wrapperDto) {
		final T entity = wrapperDto.getModel();
		if (entity instanceof RbEntityLogTimeBase) {
			((RbEntityLogTimeBase) entity).init(wrapperDto.getUsername());
		}

		int count = getDao().update(entity);
		log.debug("updateCount {}", count);
		return count;
	}

	@Override
	public int delete(RbWrapperDto<T> wrapperDto) {
		final T entity = wrapperDto.getModel();
		int count = getDao().delete(entity);
		log.debug("deleteCount {}", count);
		return count;
	}

	@Override
	public int deleteById(RbWrapperDto<Long> wrapperDto) {
		int count = 0;
		T entity = load(wrapperDto);
		if (entity != null) {
			count = getDao().delete(entity);
		} else {
			count = 0;
		}
		log.debug("deleteCount {}", count);
		return count;
	}
	
}
