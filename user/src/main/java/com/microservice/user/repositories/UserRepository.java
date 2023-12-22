package com.microservice.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.user.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
 
	
}
