package com.softpower.chihuahua.core.pagination.plugin;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.Assert;

import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.core.pagination.dialect.RbDialect;
import com.softpower.chihuahua.core.pagination.dialect.RbDialectFactory;

@Slf4j
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class RbPaginationInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler stmtHandler = (StatementHandler) invocation.getTarget();
		BoundSql boundSql = stmtHandler.getBoundSql();
		String originalSql = StringUtils.trimToEmpty(boundSql.getSql());

		if (StringUtils.isBlank(originalSql)) {
			return invocation.proceed();
		}

		Object delegate = FieldUtils.readField(stmtHandler, "delegate", true);
		RowBounds rowBounds = (RowBounds) FieldUtils.readField(delegate, "rowBounds", true);

		if (rowBounds != null && rowBounds != RowBounds.DEFAULT) {
			Assert.isInstanceOf(Pagination.class, rowBounds, "PaginationInterceptor support only " + Pagination.class.getName() + ". ");
			Pagination pagination = (Pagination) rowBounds;

			keepSql(pagination, boundSql);

			String paginationSql = toPaginationSql(originalSql, delegate, pagination);

			FieldUtils.writeField(boundSql, "sql", paginationSql, true);
		}

		return invocation.proceed();
	}

	private String toPaginationSql(String originalSql, Object delegate, Pagination Pagination) {
		RbDialect dialect = getDialect(delegate);
		return dialect.getPaginationSql(originalSql, Pagination.getPageOffset(), Pagination.getSize(), Pagination.getOrderBy());
	}

	private RbDialect getDialect(Object delegate) {
		RbDialect.TYPE dbType = getDialectType(delegate);
		return new RbDialectFactory().getPaginationDialect(dbType);
	}

	private RbDialect.TYPE getDialectType(Object delegate) {
		RbDialect.TYPE dbType = null;
		try {
			Configuration config = (Configuration) FieldUtils.readField(delegate, "configuration", true);
			dbType = RbDialect.TYPE.valueOf(config.getVariables().getProperty("dialect").toUpperCase());
			log.debug("loop up '{}' dialect type", dbType);
		} catch (Exception e) {
			log.error("{}", e);
		}

		if (dbType == null) {
			log.debug("No '{}' dialect type found", dbType);
			throw new NullPointerException(
					"configuration(mybatis-config.xml) need to add properties that name is dialect to setting db type ORACLE/MSSQL/MYSQL/...");
		}else {
			return dbType;
		}
	}

	private void keepSql(Pagination pagination, BoundSql boundSql) {
		String exactSql = getExactSql(boundSql);
		pagination.setSql(exactSql);
	}

	private String getExactSql(BoundSql boundSql) {
		List<ParameterMapping> pms = boundSql.getParameterMappings();
		String exactSql = boundSql.getSql();
		for (ParameterMapping pm : pms) {
			String value = String.valueOf(boundSql.getAdditionalParameter(pm.getProperty()));
			exactSql = StringUtils.replace(exactSql, "?", value, 1);
		}

		return exactSql;
	}


	@Override
	public Object plugin(Object obj) {
		return Plugin.wrap(obj, this);
	}


	@Override
	public void setProperties(Properties properties) {

	}

}
