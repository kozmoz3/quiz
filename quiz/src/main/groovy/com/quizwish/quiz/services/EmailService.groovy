package com.quizwish.quiz.services

interface EmailService {
	def sendMail(String toEmail, String subject, String message)
}
