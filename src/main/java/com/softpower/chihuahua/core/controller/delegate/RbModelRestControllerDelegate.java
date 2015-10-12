package com.softpower.chihuahua.core.controller.delegate;

import java.util.List;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.base.Preconditions;
import com.softpower.chihuahua.core.controller.security.RbSpringSecurity;
import com.softpower.chihuahua.core.dto.RbCond;
import com.softpower.chihuahua.core.dto.RbWrapperDto;
import com.softpower.chihuahua.core.entity.RbModel;
import com.softpower.chihuahua.core.pagination.Pagination;
import com.softpower.chihuahua.core.service.RbModelService;

@Slf4j
public class RbModelRestControllerDelegate<T extends RbModel, C extends RbCond> {

	@Getter
	private RbModelService<T, C> rbModelService;

	/* constructor */
	public RbModelRestControllerDelegate(RbModelService<T, C> rbModelService) {
		this.rbModelService = rbModelService;
	}

	/**
	 * LIST
	 * <p>
	 * @param cond
	 * @return
	 */
	public HttpEntity<List<T>> list(@RequestBody C cond) {
		log.info("Listing models {}", cond);

		List<T> cols = getRbModelService().list(RbWrapperDto.of(cond, RbSpringSecurity.getPrincipalUser()), Pagination.ALL);
		ResponseEntity<List<T>> responseEntity = new ResponseEntity<>(cols, HttpStatus.OK);

		log.debug("Listing : " + responseEntity);
		return responseEntity;
	}

	/**
	 * CREATE
	 * <p>
	 * @param model
	 * @return
	 */
	public HttpEntity<T> create(@RequestBody T model) {
		log.info("Creating new model {}", model);
		Preconditions.checkArgument(model != null);

		getRbModelService().create(RbWrapperDto.of(model, RbSpringSecurity.getPrincipalUser()));
		ResponseEntity<T> responseEntity = new ResponseEntity<>(model, HttpStatus.OK);

		log.debug("Creating : " + responseEntity);
		return responseEntity;
	}

	/**
	 * UPDATE
	 * <p>
	 * @param model
	 * @return
	 */
	public HttpEntity<Void> update(@RequestBody T model) {
		log.info("Updating model {}", model);
		Preconditions.checkArgument(model != null);

		ResponseEntity<Void> responseEntity = null;
		int cnt = getRbModelService().update(RbWrapperDto.of(model, RbSpringSecurity.getPrincipalUser()));
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
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public HttpEntity<Void> delete(@RequestBody C condition) {
		log.info("Deleting condition {}", condition);
		Preconditions.checkArgument(condition != null);

		ResponseEntity<Void> responseEntity = null;
		int cnt = getRbModelService().delete(RbWrapperDto.of(condition, RbSpringSecurity.getPrincipalUser()));
		if (cnt != 0) {
			responseEntity = new ResponseEntity<>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		log.debug("Deleting : " + responseEntity);
		return responseEntity;
	}

}
