package com.quizwish.quiz.sesion

public class AppUser {
	def userId
	def userName
	def encrytedPassword

	def AppUser() {

	}

	def AppUser(Long userId, String userName, String encrytedPassword) {
		this.userId = userId
		this.userName = userName
		this.encrytedPassword = encrytedPassword
	}

	public java.lang.Object getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Object userId) {
		this.userId = userId;
	}

	public java.lang.Object getUserName() {
		return userName;
	}

	public void setUserName(java.lang.Object userName) {
		this.userName = userName;
	}

	public java.lang.Object getEncrytedPassword() {
		return encrytedPassword;
	}

	public void setEncrytedPassword(java.lang.Object encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}	
	
}
