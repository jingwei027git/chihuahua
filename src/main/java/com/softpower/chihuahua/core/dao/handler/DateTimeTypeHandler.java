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

@MappedTypes(DateTime.class)
public class DateTimeTypeHandler implements TypeHandler<DateTime> {

	private Long getTimestampLong(Timestamp ts) {
		return (ts == null)? null: ts.getTime();
	}

	@Override
	public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
		final Long timeLong = getTimestampLong(rs.getTimestamp(columnName));
		return (timeLong == null)? null: new DateTime(timeLong);
	}

	@Override
	public DateTime getResult(ResultSet rs, int i) throws SQLException {
		final Long timeLong = getTimestampLong(rs.getTimestamp(i));
		return (timeLong == null)? null: new DateTime(timeLong);
	}

	@Override
	public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
		final Long timeLong = getTimestampLong(cs.getTimestamp(columnIndex));
		return (timeLong == null)? null: new DateTime(timeLong);
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
		if (parameter != null) {
			ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
		} else {
			ps.setTimestamp(i, null);
		}
	}

}
