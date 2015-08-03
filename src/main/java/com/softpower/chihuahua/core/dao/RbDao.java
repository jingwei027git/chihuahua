package com.softpower.chihuahua.core.dao;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

import com.softpower.chihuahua.core.entity.RbModel;

public interface RbDao<T extends RbModel, ID extends Serializable> extends Repository<T, ID> {

}
