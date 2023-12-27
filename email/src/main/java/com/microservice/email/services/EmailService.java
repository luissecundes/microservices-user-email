package com.microservice.email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservice.email.entities.EmailEntity;
import com.microservice.email.enums.StatusEmail;
import com.microservice.email.repositories.EmailRepository;

@Service
public class EmailService {
	
	final EmailRepository emailRepository;
	
	@Autowired
	final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }
    
    @Value(value = "${spring.mail.username}")
    private String emailFrom;
    
    
    @Transactional
    public EmailEntity sendEmail (EmailEntity emailEntity) {
    	try {
    		emailEntity.setSendDateEmail(LocalDateTime.now());
    		emailEntity.setEmailFrom(emailFrom);
    		
    		SimpleMailMessage message = new SimpleMailMessage();
    		message.setTo(emailEntity.getEmailTo());
    		message.setSubject(emailEntity.getSubject());
    		message.setText(emailEntity.getText());
    		emailSender.send(message);
    		
    		emailEntity.setStatusEmail(StatusEmail.ENVIADO);
    	} catch (MailException e) {
    		emailEntity.setStatusEmail(StatusEmail.ERROR);
    	} finally {
			return emailRepository.save(emailEntity);
		}
    	
    }

}
