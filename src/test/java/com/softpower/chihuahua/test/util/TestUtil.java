package com.softpower.chihuahua.test.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

public class TestUtil {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8") );

	private static final String MOCK_CHARACTER = "a";

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		return null;
	}

	public static String createStringWithLength(int length) {
		StringBuilder builder = new StringBuilder();

		for (int i=0; i<length; i++) {
			builder.append(MOCK_CHARACTER);
		}

		return builder.toString();
	}

}
