package com.softpower.chihuahua.core.util.string;

import org.junit.Assert;
import org.junit.Test;

import com.softpower.chihuahua.core.util.string.RbStringBuffers;
import com.softpower.chihuahua.test.GenericTest;


public class RbStringBuffersTest extends GenericTest {

	@Test
	public void testRemoveEnd() {
		final StringBuffer sb = new StringBuffer();
		final StringBuffer nullSb = null;

		RbStringBuffers.removeEnd(nullSb, ";");
		Assert.assertEquals(null, nullSb);

		RbStringBuffers.removeAll(sb);
		sb.append("");
		RbStringBuffers.removeEnd(sb, ";");
		Assert.assertEquals("", sb.toString());

		RbStringBuffers.removeAll(sb);
		sb.append("abc;");
		RbStringBuffers.removeEnd(sb, null);
		Assert.assertEquals("abc;", sb.toString());

		RbStringBuffers.removeAll(sb);
		sb.append("www.domain.com");
		RbStringBuffers.removeEnd(sb, ".com.");
		Assert.assertEquals("www.domain.com", sb.toString());

		RbStringBuffers.removeAll(sb);
		sb.append("www.domain.com");
		RbStringBuffers.removeEnd(sb, ".com");
		Assert.assertEquals("www.domain", sb.toString());

		RbStringBuffers.removeAll(sb);
		sb.append("www.domain.com");
		RbStringBuffers.removeEnd(sb, "domain");
		Assert.assertEquals("www.domain.com", sb.toString());

		RbStringBuffers.removeAll(sb);
		sb.append("abc");
		RbStringBuffers.removeEnd(sb, "");
		Assert.assertEquals("abc", sb.toString());

		RbStringBuffers.removeAll(sb);
		sb.append(";abc;;");
		RbStringBuffers.removeEnd(sb, ";");
		Assert.assertEquals(";abc;", sb.toString());
	}

}
