package com.quizwish.quiz.sesion

public class AppUser {
	Long userId
	String userName
	String encrytedPassword

	def AppUser() {

	}

	def AppUser(Long userId, String userName, String encrytedPassword) {
		this.userId = userId
		this.userName = userName
		this.encrytedPassword = encrytedPassword
	}	
}
