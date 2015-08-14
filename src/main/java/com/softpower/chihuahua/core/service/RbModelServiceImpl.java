package com.softpower.chihuahua.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;

@SuppressWarnings("serial")
public abstract class RbModelServiceImpl<T extends RbModel, C extends RbCond> implements RbModelService<T, C> {
	
	@Override
	public List<T> list(C condition, Pagination pagination) {
		return new ArrayList<>();
	}

	@Override
	public long create(T entity, UserDetails user) {
		return 0L;
	}

	@Override
	public int update(T entity, UserDetails user) {
		return 0;
	}

	@Override
	public int delete(C entity) {
		return 0;
	}
	
}
