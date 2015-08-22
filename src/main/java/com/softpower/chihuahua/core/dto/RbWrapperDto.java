package com.softpower.chihuahua.core.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.NonNull;

import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@SuppressWarnings("serial")
public class RbWrapperDto<T> implements RbDto {

	@Getter
	private Optional<T> modelOptional;

	@Getter
	private UserDetails principal;

	@Getter
	private Map<String, Object> appendMap = new HashMap<>();


	public static <T> RbWrapperDto<T> of(
		@NonNull T model,
		@NonNull UserDetails userDetail) {
		return new RbWrapperDto<>(model, userDetail);
	}

	private RbWrapperDto(
		@NonNull T model,
		@NonNull UserDetails userDetail)
	{
		Preconditions.checkArgument(model != null);
		Preconditions.checkArgument(userDetail != null);
		this.modelOptional = Optional.of(model);
		this.principal = userDetail;
	}

	public T getModel() {
		return modelOptional.get();
	}

	public String getUsername() {
		return principal.getUsername();
	}

	public void pubAppendData(String key, Object data) {
		getAppendMap().put(key, data);
	}

	public Object getAppendData(String key) {
		return getAppendMap().get(key);
	}

}
