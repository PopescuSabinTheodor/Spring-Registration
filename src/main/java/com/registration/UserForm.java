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
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getPasswordVerify() {
		return passwordVerify;
	}
	public void setPasswordVerify(String passwordVerify) {
		this.passwordVerify = passwordVerify;
	}
	
}
