package com.microservice.user.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.user.dtos.UserRecordDto;
import com.microservice.user.entities.UserEntity;
import com.microservice.user.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/users")
	public ResponseEntity<UserEntity> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
		var userEntity = new UserEntity();
		BeanUtils.copyProperties(userRecordDto, userEntity);		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userEntity));
	}

}
