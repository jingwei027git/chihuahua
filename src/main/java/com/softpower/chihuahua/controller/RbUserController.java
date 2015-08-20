package com.softpower.chihuahua.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softpower.chihuahua.condition.RbUserCond;
import com.softpower.chihuahua.core.controller.RbControllerBase;
import com.softpower.chihuahua.core.controller.delegate.RbEntityRestControllerDelegate;
import com.softpower.chihuahua.entity.RbUser;
import com.softpower.chihuahua.service.RbUserService;

@RestController
@RequestMapping("/users")
public class RbUserController extends RbControllerBase {

	@Resource(name = "RbUserService")
	protected RbUserService rbUserService;
	
	private RbEntityRestControllerDelegate<RbUser, RbUserCond, Long> delegate = new RbEntityRestControllerDelegate<>(rbUserService);
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<RbUser>> list() {
		return delegate.list();
	}

}
