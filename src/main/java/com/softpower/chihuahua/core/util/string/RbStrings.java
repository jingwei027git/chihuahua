package com.softpower.chihuahua.core.util.string;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public class RbStrings {

	/** ^[A-Za-z0-9_%]+$ **/
	final static Pattern SQL_LIKE_ALLOW_PTN = Pattern.compile("^[A-Za-z0-9_%]+$");

	/**
	 * convert SQL like string to Regular Expression
	 * <p>
	 * sqlLike only support alphabet plus _ and %
	 * <p>
	 * example : <br>
	 * 1. bcd to ^bcd$ <br>
	 * 2. %bcd to ^.*bcd$ <br>
	 * 3. bcd% to ^bcd.*$ <br>
	 * 4. %bcd% to ^.*bcd.*$ <br>
	 * 5. _bcd to ^.{1}bcd$ <br>
	 * 6. bcd_ to ^bcd.{1}$ <br>
	 * 7. _bcd_ to ^.{1}bcd.{1}$ <br>
	 * 8. bcd__ to ^bcd.{1}.{1}$ <br>
	 * <p>
	 * @param sqlLike
	 * @return
	 */
	public static Pattern convertSqlLikeToRegex(String sqlLike) {
		Preconditions.checkArgument(StringUtils.isNotBlank(sqlLike));
		Preconditions.checkArgument(SQL_LIKE_ALLOW_PTN.matcher(sqlLike).matches(), "sqlLike only allow Pattern {" + SQL_LIKE_ALLOW_PTN.pattern() + "}");

		sqlLike = sqlLike.replaceAll("%", ".*");
		sqlLike = sqlLike.replaceAll("_", ".");

		return Pattern.compile("^" + sqlLike + "$");
	}
	
	public static String concats(Object ... objs) {
		StringBuffer buf = new StringBuffer("");
		if (objs == null || objs.length < 1) return buf.toString();
		
		for (Object obj : objs) {
			if (obj == null) continue;
			buf.append(obj.toString());
		}
		
		return buf.toString();
	}
	
	public static String newline() {
		return System.getProperty("line.separator");
	}
	
	public static String begline() {
		return "";
	}
	
	public static String endline() {
		return "";
	}

}
