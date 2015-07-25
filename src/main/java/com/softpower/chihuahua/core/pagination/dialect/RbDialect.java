package com.softpower.chihuahua.core.pagination.dialect;

import com.softpower.chihuahua.core.pagination.OrderBy;

public interface RbDialect {

	public enum TYPE {
		H2, MYSQL, MSSQL, ORACLE
	};

	public String getPaginationSql(String sql, int offset, int limit, OrderBy orderBy);

}
