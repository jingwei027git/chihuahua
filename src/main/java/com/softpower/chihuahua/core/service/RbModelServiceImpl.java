package com.softpower.chihuahua.core.service;

import java.util.ArrayList;
import java.util.List;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;

public abstract class RbModelServiceImpl<T extends RbModel, C extends RbCond>
	implements RbModelService<T, C>
{
	@Override
	public List<T> list(RbWrapperDto<C> condition, Pagination pagination) {
		return new ArrayList<>();
	}

	@Override
	public long create(RbWrapperDto<T> entity) {
		return 0L;
	}

	@Override
	public int update(RbWrapperDto<T> entity) {
		return 0;
	}

	@Override
	public int delete(RbWrapperDto<C> entity) {
		return 0;
	}
	
}
