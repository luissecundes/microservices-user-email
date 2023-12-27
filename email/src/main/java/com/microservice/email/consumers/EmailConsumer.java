package com.microservice.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.microservice.email.dtos.EmailRecordDto;
import com.microservice.email.entities.EmailEntity;
import com.microservice.email.services.EmailService;

@Component
public class EmailConsumer {
	
	final EmailService emailService;
	
	public EmailConsumer(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
		var emailEntity = new EmailEntity();
		BeanUtils.copyProperties(emailRecordDto, emailEntity);
		emailService.sendEmail(emailEntity);
	}

}
