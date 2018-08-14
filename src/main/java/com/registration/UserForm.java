package com.registration;

/**
 * User form data model
 *
 */
public class UserForm {
	
	/**
	 * User's first name field 
	 */
	private String firstName;
	/**
	 * User's last name field
	 */
	private String lastName;
	/**
	 * User's email address field
	 */
	private String email;
	/**
	 * User's password field
	 */
	private String password;
	/**
	 * User's matching password field
	 */
	private String passwordVerify;
	/**
	 * Getter
	 * @return the user's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setter
	 * @param firstName the user's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Getter
	 * @return the user's last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setter
	 * @param lastName the user's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Getter
	 * @return the user's email address
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter
	 * @param email the user's email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Getter
	 * @return the user's password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Setter
	 * @param password the user's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Getter
	 * @return the user's matching password
	 */
	public String getPasswordVerify() {
		return passwordVerify;
	}
	/**
	 * Setter
	 * @param passwordVerify the user's matching password
	 */
	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}
	
}
