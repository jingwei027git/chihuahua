package com.softpower.chihuahua.core.pagination.dialect;

import com.softpower.chihuahua.core.pagination.dialect.impl.H2Dialect;

public class RbDialectFactory {

	public RbDialect getPaginationDialect(RbDialect.TYPE dialect) {
		switch (dialect) {
		case H2:
			return new H2Dialect();
		default:
			throw new UnsupportedOperationException();
		}
	}
}
