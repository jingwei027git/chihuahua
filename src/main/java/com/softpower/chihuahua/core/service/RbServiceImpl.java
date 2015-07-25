package com.softpower.chihuahua.core.service;

import com.softpower.chihuahua.core.entity.RbModel;

@SuppressWarnings("serial")
public abstract class RbServiceImpl<T extends RbModel> implements RbService<T> {

//	@Getter
//	private Class<T> modelClass = null;
//
//	@Getter
//	private T modelInstance = null;

//	private ParameterizedType getParameterizedType() {
//		return (ParameterizedType) getClass().getGenericSuperclass();
//	}

	protected RbServiceImpl() {
//		try {
//			this.modelClass = (Class<T>) getParameterizedType().getActualTypeArguments()[0];
//			this.modelInstance = modelClass.newInstance();
//		}catch(Exception e) {
//			throw new RuntimeException(e);
//		}
	}

}
