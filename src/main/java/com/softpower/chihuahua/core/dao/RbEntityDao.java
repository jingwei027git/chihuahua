package com.softpower.chihuahua.core.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.data.repository.Repository;

import com.softpower.chihuahua.core.dao.provider.MybatisSqlProvider;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.pagination.Pagination;

public interface RbEntityDao<T extends RbEntity, ID extends Serializable> extends RbModelDao<T>, Repository<T, ID> {

	/** should implement in DAO.XML **/
	public T findOne(@Param("id") ID id);

	/** should implement in DAO.XML **/
	public List<T> findAll(@Param("cond") RbCond condition, Pagination pagination);

	/** should implement in DAO.XML **/
	public long count(@Param("cond") RbCond condition);

	@InsertProvider(type = MybatisSqlProvider.class, method = "create")
	public <S extends T> int save(S entity);

	@UpdateProvider(type = MybatisSqlProvider.class, method = "update")
	public int update(T entity);

	@DeleteProvider(type = MybatisSqlProvider.class, method = "delete")
	public int delete(T entity);

}
