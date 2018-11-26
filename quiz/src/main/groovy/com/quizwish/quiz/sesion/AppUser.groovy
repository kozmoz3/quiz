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

	def Long getUserId() {
		return userId
	}

	def setUserId(Long userId) {
		this.userId = userId
	}

	def String getUserName() {
		return userName
	}

	def setUserName(String userName) {
		this.userName = userName
	}

	def String getEncrytedPassword() {
		return encrytedPassword
	}

	def setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword
	}	
	
}
