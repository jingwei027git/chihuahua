package com.softpower.chihuahua.core.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbModelService<T extends RbModel, C extends RbCond> extends RbService {
	
	public List<T> list(C condition, Pagination pagination);

	public long create(T entity, UserDetails user);

	public int update(T entity, UserDetails user);

	public int delete(C entity);
	
}
