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
	
	/**
	 * Getter
	 * @return 
	 */
	public boolean isEmailExists() {
		return emailExists;
	}
	/**
	 * Setter
	 * @param emailExists
	 */
	public void setEmailExists(boolean emailExists) {
		this.emailExists = emailExists;
	}
	
	/**
	 * Overridden toString function for {@link UserValidation} object
	 */
	@Override
	public String toString() {
		return "UserValidation [emailExists=" + emailExists + "]";
	}
	
}
