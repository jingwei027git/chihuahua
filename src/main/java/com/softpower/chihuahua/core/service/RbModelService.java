package com.softpower.chihuahua.core.service;

import java.util.List;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbModelService<T extends RbModel, C extends RbCond>
	extends RbService
{
	public List<T> list(RbWrapperDto<C> wrapperDto, Pagination pagination);

	public long create(RbWrapperDto<T> wrapperDto);

	public int update(RbWrapperDto<T> wrapperDto);

	public int delete(RbWrapperDto<C> wrapperDto);
	
}
