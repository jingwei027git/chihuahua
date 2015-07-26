package com.softpower.chihuahua.core.pagination.dialect;

import com.softpower.chihuahua.core.pagination.dialect.impl.H2Dialect;
import com.softpower.chihuahua.core.pagination.dialect.impl.MySQLDialect;

public class RbDialectFactory {

	public RbDialect getPaginationDialect(RbDialect.TYPE dialect) {
		switch (dialect) {
		case H2:
			return new H2Dialect();
		case MYSQL:
			return new MySQLDialect();
		default:
			throw new UnsupportedOperationException();
		}
	}
}
