package com.softpower.chihuahua.core.service;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.core.util.string.RbStrings;

public abstract class RbModelServiceImpl<T extends RbModel, C extends RbCond>
	implements RbModelService<T, C>
{
	private String getNotImplementErrMsg(String methodName) {
		return RbStrings.concats("Service : ", getClass().getName(), " method [", methodName, "] not implement");
	}

	@Override
	public List<T> list(RbWrapperDto<C> condition, Pagination pagination) {
		throw new NotImplementedException(getNotImplementErrMsg("list"));
	}

	@Override
	public long create(RbWrapperDto<T> entity) {
		throw new NotImplementedException(getNotImplementErrMsg("create"));
	}

	@Override
	public int update(RbWrapperDto<T> entity) {
		throw new NotImplementedException(getNotImplementErrMsg("update"));
	}

	@Override
	public int delete(RbWrapperDto<C> entity) {
		throw new NotImplementedException(getNotImplementErrMsg("delete"));
	}

}
