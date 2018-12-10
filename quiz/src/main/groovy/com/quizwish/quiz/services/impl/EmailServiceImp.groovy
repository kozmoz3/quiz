package com.quizwish.quiz.services.impl

import com.quizwish.quiz.services.EmailService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service("servicioMails")
class EmailServiceImp implements EmailService{

	@Autowired
	SimpleMailMessage simpleMailMessage
	
	JavaMailSender javaMailSender

	@Autowired
	public EmailServiceImp(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender
	}

	@Override
	def sendMail(String toEmail, String subject, String message) {		
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("admin@admin.com");
        javaMailSender.send(simpleMailMessage);
	}
}
