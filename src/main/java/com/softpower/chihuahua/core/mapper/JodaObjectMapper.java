package com.softpower.chihuahua.core.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@SuppressWarnings("serial")
public class JodaObjectMapper extends ObjectMapper {

	public JodaObjectMapper() {
		super();
		registerModule(new JodaModule());
	}

}
