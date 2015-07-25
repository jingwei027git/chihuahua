package com.softpower.chihuahua.core.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import com.softpower.chihuahua.core.dao.provider.MybatisSqlProvider;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbEntityDao<T extends RbEntity, PK extends Serializable> extends RbDao<T, PK> {

	public Long count(Object expression);

	/** should implement in DAO.XML **/
	public <E extends T> E read(@Param("id") Long id);

	/** should implement in DAO.XML **/
	public <E extends T> List<E> readAll(@Param("cond") RbModel condition, Pagination pagination);

	@InsertProvider(type = MybatisSqlProvider.class, method = "create")
	public <E extends T> Integer create(E entity);

	@UpdateProvider(type = MybatisSqlProvider.class, method = "update")
	public <E extends T> Integer update(E entity);

	@DeleteProvider(type = MybatisSqlProvider.class, method = "delete")
	public <E extends T> Integer delete(E entity);

}
