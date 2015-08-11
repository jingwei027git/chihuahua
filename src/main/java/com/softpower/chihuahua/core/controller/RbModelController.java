package com.softpower.chihuahua.core.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.core.service.RbModelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RbModelController<T extends RbModel, C extends RbCond> implements RbController {

	protected abstract RbModelService<T, C> getRbModelService();
	
	protected void beforeCreate(T t) { }
	protected void beforeUpdate(T t) { }
	protected void beforeDelete(T t) { }
	
	protected void afterCreate(T t) { }
	protected void afterUpdate(T t) { }
	protected void afterDelete(T t) { }
	

	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<T> create(@RequestBody T t) {
		log.info("Creating new entity {}", t);
		Preconditions.checkArgument(t != null);

		getRbModelService().create(t, getPrincipalUser());

        return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody T t) {
		log.info("Updating entity {}", t);
		Preconditions.checkArgument(t != null);

		int cnt = getRbModelService().update(t, getPrincipalUser());
		if (cnt != 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@RequestBody C c) {
		log.info("Deleting entity {}", c);
		Preconditions.checkArgument(c != null);

		int cnt = getRbModelService().delete(c);
		if (cnt != 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<Iterable<T>> list() {
		log.info("Listing entities");
		List<T> list = getRbModelService().list(null, Pagination.ALL);
		if (Iterables.isEmpty(list)) {
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}
	
}
