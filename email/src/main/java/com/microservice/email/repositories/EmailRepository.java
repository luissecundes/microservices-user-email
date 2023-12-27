package com.microservice.email.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.email.entities.EmailEntity;

public interface EmailRepository extends JpaRepository<EmailEntity, UUID> {

}
