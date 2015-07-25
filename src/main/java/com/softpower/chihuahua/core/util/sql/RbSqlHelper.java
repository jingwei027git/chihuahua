package com.softpower.chihuahua.core.util.sql;

import java.lang.reflect.Field;

import com.google.common.base.CaseFormat;

public class RbSqlHelper {

	public static final String CGLIB_PROXY_PREFIX = "$$EnhancerByCGLIB$$";

	public static String toTableName(Class<?> c) {
		String className = c.getSimpleName();
		int inx = className.indexOf(CGLIB_PROXY_PREFIX);
		if (inx != -1) {
			className = className.substring(0, inx);
		}

		return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, className);
	}

	public static String toColumnName(Field field) {
		return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field.getName());
	}

}
