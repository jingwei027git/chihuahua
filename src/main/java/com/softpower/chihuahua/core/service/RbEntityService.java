package com.softpower.chihuahua.core.service;

import java.io.Serializable;
import java.util.List;

import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbEntityService<T extends RbEntity, C extends RbModel, PK extends Serializable>
	extends RbService<T> {

	public T read(PK pk);

	public List<T> list(C condition, Pagination pagination);

	public Integer create(T entity);

	public Integer update(T entity);

	public Integer delete(T entity);

	public Integer delete(PK pk);

}
