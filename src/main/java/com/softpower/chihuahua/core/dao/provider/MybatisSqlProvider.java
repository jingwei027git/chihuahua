package com.softpower.chihuahua.core.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.persistence.Transient;

import com.google.common.collect.Lists;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.util.sql.RbSqlHelper;

public class MybatisSqlProvider {

	private List<Field> getFields(RbEntity entity) {
		List<Field> fields = Lists.newArrayList();
		for (Class<?> clazz = entity.getClass(); (RbEntity.class.isAssignableFrom(clazz)); clazz = clazz.getSuperclass()) {
			FIELDS: for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);
				if (Modifier.isFinal(field.getModifiers())) {
					continue FIELDS;
				}
				if (field.getAnnotation(Transient.class) != null) {
					continue FIELDS;
				}
				fields.add(field);
			}
		}
		return fields;
	}

	public String create(RbEntity entity) {
		BEGIN();
		INSERT_INTO(RbSqlHelper.toTableName(entity.getClass()));
		for (Field field : getFields(entity)) {
			VALUES(RbSqlHelper.toColumnName(field), new StringBuffer("#{").append(field.getName()).append("}").toString());
		}
		return SQL();
	}

	public String update(RbEntity entity) {
		BEGIN();
		UPDATE(RbSqlHelper.toTableName(entity.getClass()));
		for (Field field : getFields(entity)) {
			SET(new StringBuffer(RbSqlHelper.toColumnName(field)).append(" = #{").append(field.getName()).append("}").toString());
		}
		WHERE("ID = #{id}");
		return SQL();
	}

	public String delete(RbEntity entity) {
		BEGIN();
		DELETE_FROM(RbSqlHelper.toTableName(entity.getClass()));
		WHERE("ID = #{id}");
		return SQL();
	}

}
