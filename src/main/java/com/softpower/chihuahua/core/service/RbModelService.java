package com.softpower.chihuahua.core.service;

import java.util.List;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.entity.RbUser;

public interface RbModelService<T extends RbModel, C extends RbCond> extends RbService {
	
	public List<T> list(C condition, Pagination pagination);

	public int create(T entity, RbUser user);

	public int update(T entity, RbUser user);

	public int delete(C entity);
	
}
