package com.softpower.chihuahua.core.controller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.core.service.RbEntityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RbEntityRestController<T extends RbEntity, C extends RbCond> extends RbControllerBase {

	protected abstract RbEntityService<T, C, Long> getRbEntityService();

	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
	public HttpEntity<T> create(@RequestBody T t) {
		log.info("Creating new entity {}", t);
		Preconditions.checkArgument(t != null);

		getRbEntityService().create(t);

        return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
	public HttpEntity<T> read(@PathVariable(value = "id") Long id) {
		log.info("Reading entity with id {}", id);
		Preconditions.checkArgument(id != null);

        T t = getRbEntityService().load(id);
        if (t != null) {
        	return new ResponseEntity<>(t, HttpStatus.OK);
        }else {
        	return new ResponseEntity<>(HttpStatus.OK);
        }
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Void> update(@PathVariable(value = "id") Long id, @RequestBody T t) {
		log.info("Updating entity with id {} with {}", id, t);
		Preconditions.checkArgument(id != null);
		Preconditions.checkArgument(t != null);
		Preconditions.checkArgument(!id.equals(t.getId()), "id doesn't match URL id: {}", t.getId());

		int cnt = getRbEntityService().update(t);
		if (cnt != 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public HttpEntity<Void> delete(@PathVariable(value = "id") Long id) {
		log.info("Deleting entity with id {}", id);
		Preconditions.checkArgument(id != null);

		int cnt = getRbEntityService().delete(id);
		if (cnt != 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<List<T>> list() {
		log.info("Listing entities");
		List<T> list = getRbEntityService().list(null, Pagination.ALL);
		if (Iterables.isEmpty(list)) {
			return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

}
