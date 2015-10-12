package com.softpower.chihuahua.core.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.repository.Repository;

import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbEntityViewDao<T extends RbEntity, ID extends Serializable> extends RbModelDao<T>, Repository<T, ID> {

	/** should implement in DAO.XML **/
	public T findOne(@Param("id") ID id);

	/** should implement in DAO.XML **/
	public List<T> findAll(@Param("cond") RbCond condition, Pagination pagination);

	/** should implement in DAO.XML **/
	public long count(@Param("cond") RbCond condition);

}
