package com.microservice.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.user.entities.UserEntity;
import com.microservice.user.producers.UserProducer;
import com.microservice.user.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserProducer userProducer;
	
	@Transactional
	public UserEntity save (UserEntity userEntity) {
		userEntity = userRepository.save(userEntity);
		userProducer.publishMessageEmail(userEntity);
		return userEntity;
	}
}
