package com.registration;

/**
 * Class used to validate a user's fields
 *
 */
public class UserValidation {
	
	/**
	 * Boolean used to check if the email address is already in the database
	 * Default is false
	 */
	private boolean emailExists = false;
	
	
	public boolean isEmailExists() {
		return emailExists;
	}
	public void setEmailExists(boolean emailExists) {
		this.emailExists = emailExists;
	}
	
	
	@Override
	public String toString() {
		return "UserValidation [emailExists=" + emailExists + "]";
	}
	
}
