package com.softpower.chihuahua.core.controller.delegate;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

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
import com.softpower.chihuahua.core.controller.security.RbSpringSecurity;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.entity.RbEntity;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.core.service.RbEntityService;

@Slf4j
public class RbEntityRestControllerDelegate<T extends RbEntity, C extends RbCond, PK extends Serializable> {

	@Getter
	private RbEntityService<T, C, Long> rbEntityService;

	/* constructor */
	public RbEntityRestControllerDelegate(RbEntityService<T, C, Long> rbEntityService) {
		this.rbEntityService = rbEntityService;
	}

	/**
	 * CREATE
	 * <p>
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<T> create(@RequestBody T entity) {
		log.info("Creating new entity {}", entity);
		Preconditions.checkArgument(entity != null);

		getRbEntityService().create(RbWrapperDto.of(entity, RbSpringSecurity.getPrincipalUser()));
		ResponseEntity<T> responseEntity = new ResponseEntity<>(entity, HttpStatus.OK);

		log.debug("Creating : " + responseEntity);
		return responseEntity;
	}

	/**
	 * READ
	 * <p>
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<T> read(@PathVariable(value = "id") Long id) {
		log.info("Reading entity with id {}", id);
		Preconditions.checkArgument(id != null);

		ResponseEntity<T> responseEntity = null;
		T t = getRbEntityService().load(RbWrapperDto.of(id, RbSpringSecurity.getPrincipalUser()));
		if (t != null) {
			responseEntity = new ResponseEntity<>(t, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		}

		log.debug("Reading : " + responseEntity);
		return responseEntity;
	}

	/**
	 * UPDATE
	 * <p>
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public HttpEntity<Void> update(@PathVariable(value = "id") Long id, @RequestBody T entity) {
		log.info("Updating entity with id {} with {}", id, entity);
		Preconditions.checkArgument(id != null);
		Preconditions.checkArgument(entity != null);
		Preconditions.checkArgument(!id.equals(entity.getId()), "id doesn't match URL id: {}", entity.getId());

		ResponseEntity<Void> responseEntity = null;
		int cnt = getRbEntityService().update(RbWrapperDto.of(entity, RbSpringSecurity.getPrincipalUser()));
		if (cnt != 0) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		log.debug("Updating : " + responseEntity);
		return responseEntity;
	}

	/**
	 * DELETE
	 * <p>
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public HttpEntity<Void> delete(@PathVariable(value = "id") Long id) {
		log.info("Deleting entity with id {}", id);
		Preconditions.checkArgument(id != null);

		ResponseEntity<Void> responseEntity = null;
		int cnt = getRbEntityService().deleteById(RbWrapperDto.of(id, RbSpringSecurity.getPrincipalUser()));
		if (cnt != 0) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		log.debug("Deleting : " + responseEntity);
		return responseEntity;
	}

	/**
	 * LIST
	 * <p>
	 * @return
	 */
	public ResponseEntity<List<T>> list() {
		log.info("Listing entities");

		ResponseEntity<List<T>> responseEntity = null;
		List<T> list = getRbEntityService().list(null, Pagination.ALL);
		if (Iterables.isEmpty(list)) {
			responseEntity = new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		}

		log.debug("Listing : " + responseEntity);
		return responseEntity;
	}

}
