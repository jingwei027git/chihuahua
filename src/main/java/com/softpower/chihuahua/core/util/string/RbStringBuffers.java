package com.softpower.chihuahua.core.util.string;

import org.apache.commons.lang3.StringUtils;

public class RbStringBuffers {

	/**
	 * remove all string
	 * <p>
	 * <p>
	 * @param sb
	 * @return
	 */
	public static StringBuffer removeAll(StringBuffer sb) {
		return (sb == null)? null: sb.delete(0, sb.length());
	}

	/**
     * <p>Removes a substring only if it is at the end of a source string</p>
     *
     * <p>A {@code null} source string will return {@code null}.
     * An empty ("") source string will return the empty string.
     * A {@code null} search string will return the source string.</p>
     *
     * <pre>
     * removeEnd(null, *)      = null
     * removeEnd("", *)        = ""
     * removeEnd(*, null)      = *
     * removeEnd("www.domain.com", ".com.")  = "www.domain.com"
     * removeEnd("www.domain.com", ".com")   = "www.domain"
     * removeEnd("www.domain.com", "domain") = "www.domain.com"
     * removeEnd("abc", "")    = "abc"
     * removeEnd(";abc;;", ";" = ";abc;"
     * </pre>
     *
     * @param sb  the source String to search, may be null
     * @param remove  the String to search for and remove, may be null
     * @return
     */
	public static StringBuffer removeEnd(StringBuffer sb, final String remove) {
		if (StringUtils.isEmpty(sb) || StringUtils.isEmpty(remove)) {
            return sb;
        }
		if (sb.lastIndexOf(remove) == (sb.length() - remove.length())) {
			sb.delete(sb.length() - remove.length(), sb.length());
		}
        return sb;
	}

}
