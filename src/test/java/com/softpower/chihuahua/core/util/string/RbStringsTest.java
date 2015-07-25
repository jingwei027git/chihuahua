package com.softpower.chihuahua.core.util.string;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.softpower.chihuahua.core.util.string.RbStrings;
import com.softpower.chihuahua.test.GenericTest;


public class RbStringsTest extends GenericTest {

	@Test
	public void testConvertSqlLikeToRegex() {
		String sqlLike = null;
		Pattern ptn = null;

		sqlLike = "bcd";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^bcd$", ptn.pattern());
		Assert.assertEquals(true, ptn.matcher("bcd").matches());
		Assert.assertEquals(false, ptn.matcher("bcd ").matches());
		Assert.assertEquals(false, ptn.matcher("bcdX").matches());
		Assert.assertEquals(false, ptn.matcher(" bcd").matches());
		Assert.assertEquals(false, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "%bcd";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^.*bcd$", ptn.pattern());
		Assert.assertEquals(true, ptn.matcher("bcd").matches());
		Assert.assertEquals(false, ptn.matcher("bcd ").matches());
		Assert.assertEquals(false, ptn.matcher("bcdX").matches());
		Assert.assertEquals(true, ptn.matcher(" bcd").matches());
		Assert.assertEquals(true, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "_bcd";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^.bcd$", ptn.pattern());
		Assert.assertEquals(false, ptn.matcher("bcd").matches());
		Assert.assertEquals(false, ptn.matcher("bcd ").matches());
		Assert.assertEquals(false, ptn.matcher("bcdX").matches());
		Assert.assertEquals(true, ptn.matcher(" bcd").matches());
		Assert.assertEquals(true, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "bcd%";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^bcd.*$", ptn.pattern());
		Assert.assertEquals(true, ptn.matcher("bcd").matches());
		Assert.assertEquals(true, ptn.matcher("bcd ").matches());
		Assert.assertEquals(true, ptn.matcher("bcdX").matches());
		Assert.assertEquals(false, ptn.matcher(" bcd").matches());
		Assert.assertEquals(false, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "bcd_";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^bcd.$", ptn.pattern());
		Assert.assertEquals(false, ptn.matcher("bcd").matches());
		Assert.assertEquals(true, ptn.matcher("bcd ").matches());
		Assert.assertEquals(true, ptn.matcher("bcdX").matches());
		Assert.assertEquals(false, ptn.matcher(" bcd").matches());
		Assert.assertEquals(false, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "%bcd%";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^.*bcd.*$", ptn.pattern());
		Assert.assertEquals(true, ptn.matcher("bcd").matches());
		Assert.assertEquals(true, ptn.matcher("bcd ").matches());
		Assert.assertEquals(true, ptn.matcher("bcdX").matches());
		Assert.assertEquals(true, ptn.matcher(" bcd").matches());
		Assert.assertEquals(true, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(true, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "_bcd_";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^.bcd.$", ptn.pattern());
		Assert.assertEquals(false, ptn.matcher("bcd").matches());
		Assert.assertEquals(false, ptn.matcher("bcd ").matches());
		Assert.assertEquals(false, ptn.matcher("bcdX").matches());
		Assert.assertEquals(false, ptn.matcher(" bcd").matches());
		Assert.assertEquals(false, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(true, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "%%bcd%%"; // regard %% as %
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^.*.*bcd.*.*$", ptn.pattern());
		Assert.assertEquals(true, ptn.matcher("bcd").matches());
		Assert.assertEquals(true, ptn.matcher("bcd ").matches());
		Assert.assertEquals(true, ptn.matcher("bcdX").matches());
		Assert.assertEquals(true, ptn.matcher(" bcd").matches());
		Assert.assertEquals(true, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(true, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "bcd__";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^bcd..$", ptn.pattern());
		Assert.assertEquals(false, ptn.matcher("bcd").matches());
		Assert.assertEquals(false, ptn.matcher("bcd ").matches());
		Assert.assertEquals(false, ptn.matcher("bcdX").matches());
		Assert.assertEquals(false, ptn.matcher(" bcd").matches());
		Assert.assertEquals(false, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "%";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^.*$", ptn.pattern());
		Assert.assertEquals(true, ptn.matcher("bcd").matches());
		Assert.assertEquals(true, ptn.matcher("bcd ").matches());
		Assert.assertEquals(true, ptn.matcher("bcdX").matches());
		Assert.assertEquals(true, ptn.matcher(" bcd").matches());
		Assert.assertEquals(true, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(true, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(true, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(true, ptn.matcher("efg").matches());

		sqlLike = "_";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^.$", ptn.pattern());
		Assert.assertEquals(false, ptn.matcher("bcd").matches());
		Assert.assertEquals(false, ptn.matcher("bcd ").matches());
		Assert.assertEquals(false, ptn.matcher("bcdX").matches());
		Assert.assertEquals(false, ptn.matcher(" bcd").matches());
		Assert.assertEquals(false, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(false, ptn.matcher("efg").matches());

		sqlLike = "___";
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^...$", ptn.pattern());
		Assert.assertEquals(true, ptn.matcher("bcd").matches());
		Assert.assertEquals(false, ptn.matcher("bcd ").matches());
		Assert.assertEquals(false, ptn.matcher("bcdX").matches());
		Assert.assertEquals(false, ptn.matcher(" bcd").matches());
		Assert.assertEquals(false, ptn.matcher("Xbcd").matches());
		Assert.assertEquals(false, ptn.matcher("XbcdX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXX").matches());
		Assert.assertEquals(false, ptn.matcher("bcdXXX").matches());
		Assert.assertEquals(true, ptn.matcher("efg").matches());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConvertSqlLikeToRegexErrorCase01() {
		String sqlLike = null;
		Pattern ptn = null;

		sqlLike = "bcd "; // not support white-space
		ptn = RbStrings.convertSqlLikeToRegex(sqlLike);
		Assert.assertEquals("^bcd$", ptn.pattern());
	}

}
