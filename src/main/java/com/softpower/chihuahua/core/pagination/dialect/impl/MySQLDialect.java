package com.softpower.chihuahua.core.pagination.dialect.impl;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Iterables;
import com.softpower.chihuahua.core.pagination.Order;
import com.softpower.chihuahua.core.pagination.OrderBy;
import com.softpower.chihuahua.core.pagination.dialect.RbDialectBase;
import com.softpower.chihuahua.core.util.string.RbStringBuffers;

@Slf4j
public class MySQLDialect extends RbDialectBase {

	@Override
	public String getPaginationSql(String sql, int offset, int limit, OrderBy sort) {
		sql = StringUtils.trimToEmpty(sql);

		boolean isForUpdate = false;
		if (sql.toUpperCase().endsWith(" FOR UPDATE")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		int begRec = offset + 0;
		StringBuffer pagingSql = new StringBuffer("");
		pagingSql.append("SELECT __t.* FROM (");
		pagingSql.append(sql);
		pagingSql.append(") __t");
		pagingSql.append(" ORDER BY ");
		if (Iterables.size(sort.getOrders()) > 0) {
			for (Order order : sort.getOrders()) {
				String dbname = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, order.getName());
				String direction = order.getOption().toString();
				pagingSql.append("__t.").append(dbname).append(" ").append(direction).append(",");
			}
			RbStringBuffers.removeEnd(pagingSql, ",");
		} else {
			pagingSql.append("__t.ID");
		}
		pagingSql.append(" LIMIT ").append(begRec).append(", ").append(limit);

		if (isForUpdate) {
			pagingSql.append(" FOR UPDATE");
		}

		log.trace("pagingSql : \n" + pagingSql);

		return pagingSql.toString();
	}

}
