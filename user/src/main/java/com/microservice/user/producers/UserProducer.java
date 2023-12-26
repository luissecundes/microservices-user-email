package com.microservice.user.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microservice.user.dtos.EmailDto;
import com.microservice.user.entities.UserEntity;

@Component
public class UserProducer {
	
	final RabbitTemplate rabbitTemplate;
	
	
	public UserProducer (RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		
	}
	
	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(UserEntity userEntity) {
		var emailDto = new EmailDto();
		emailDto.setUserId(userEntity.getUserId());
		emailDto.setEmailTo(userEntity.getEmail());
		emailDto.setSubject("Cadastro realizado com sucesso!");
		emailDto.setText(userEntity.getName() + ", seja bem-vindo(a)! Agradecemos o seu cadastro.");
		
		rabbitTemplate.convertAndSend("", routingKey, emailDto);;
		
	}

}
