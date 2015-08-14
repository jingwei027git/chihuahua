package com.softpower.chihuahua.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.service.RbModelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class RbModelController<T extends RbModel, C extends RbCond> extends RbControllerBase {

	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected HttpServletResponse response;
	
	protected abstract RbModelService<T, C> getRbModelService();
	

	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
	public HttpEntity<T> create(@RequestBody T t) {
		log.info("Creating new entity {}", t);
		Preconditions.checkArgument(t != null);

		getRbModelService().create(t, getPrincipalUser());

        return new ResponseEntity<>(t, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Void> update(@RequestBody T t) {
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
	public HttpEntity<Void> delete(@RequestBody C c) {
		log.info("Deleting entity {}", c);
		Preconditions.checkArgument(c != null);

		int cnt = getRbModelService().delete(c);
		if (cnt != 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
