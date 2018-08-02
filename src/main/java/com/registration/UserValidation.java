package com.registration;

public class UserValidation {

	private	boolean usernameExists = false;
	private boolean emailExists = false;
	
	
	public boolean isUsernameExists() {
		return usernameExists;
	}
	public void setUsernameExists(boolean usernameExists) {
		this.usernameExists = usernameExists;
	}
	public boolean isEmailExists() {
		return emailExists;
	}
	public void setEmailExists(boolean emailExists) {
		this.emailExists = emailExists;
	}
	@Override
	public String toString() {
		return "UserValidation [usernameExists=" + usernameExists + ", emailExists=" + emailExists + "]";
	}
	
}
