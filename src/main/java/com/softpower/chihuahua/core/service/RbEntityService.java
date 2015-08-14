package com.softpower.chihuahua.core.service;

import java.io.Serializable;
import java.util.List;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbEntityService<T extends RbEntity, C extends RbCond, PK extends Serializable> extends RbService {

	public T load(PK pk);

	public List<T> list(C condition, Pagination pagination);

	public long create(T entity);

	public int update(T entity);

	public int delete(T entity);

	public int delete(PK pk);

}
