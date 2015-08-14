package com.softpower.chihuahua.core.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {
	
	@ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleClientErrors(Exception ex) {
		log.error(getClass().getSimpleName(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleServerErrors(Exception ex) {
    	log.error(getClass().getSimpleName(), ex);
    	return new ResponseEntity<>((ex == null)? null: ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
