package com.quizwish.quiz.models

class UserCredential {
 
	private String email;
	
	private String password;

	public UserCredential(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserCredential() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
