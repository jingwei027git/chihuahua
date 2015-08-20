package com.softpower.chihuahua.core.service;

import java.io.Serializable;
import java.util.List;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbEntityService<T extends RbEntity, C extends RbCond, PK extends Serializable>
	extends RbService
{
	public T load(RbWrapperDto<PK> pkDto);

	public List<T> list(RbWrapperDto<C> wrapperDto, Pagination pagination);

	public long create(RbWrapperDto<T> wrapperDto);

	public int update(RbWrapperDto<T> wrapperDto);

	public int delete(RbWrapperDto<T> wrapperDto);

	public int deleteById(RbWrapperDto<PK> wrapperDto);
	
}
