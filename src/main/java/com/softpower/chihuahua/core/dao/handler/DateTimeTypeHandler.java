package com.softpower.chihuahua.core.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@MappedTypes(DateTime.class)
public class DateTimeTypeHandler implements TypeHandler<DateTime> {

	public DateTime toDateTime(Timestamp ts) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		return formatter.parseDateTime(ts.toString());
	}

	public Timestamp toTimestamp(DateTime dt) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		return Timestamp.valueOf(formatter.print(dt));
	}

	@Override
	public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp ts = rs.getTimestamp(columnName);
		if (ts != null) {
			return toDateTime(ts);
		} else {
			return null;
		}
	}

	@Override
	public DateTime getResult(ResultSet rs, int i) throws SQLException {
		Timestamp ts = rs.getTimestamp(i);
		if (ts != null) {
			return toDateTime(ts);
		} else {
			return null;
		}
	}

	@Override
	public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp ts = cs.getTimestamp(columnIndex);
		if (ts != null) {
			return toDateTime(ts);
		} else {
			return null;
		}
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null) {
			ps.setTimestamp(i, toTimestamp(parameter));
		} else {
			ps.setTimestamp(i, null);
		}
	}

}
