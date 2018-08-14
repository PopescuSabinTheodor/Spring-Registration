package com.registration;
/**
 * Password change form model class used for collecting it's data 
 */
public class PasswordForm {
	/**
	 * The user's password
	 */
	private String password;
	/**
	 * The unique key of a password reset instance
	 */
	private String updateKey;
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
	 * @return the unique key of a password reset instance
	 */
	public String getUpdateKey() {
		return updateKey;
	}
	/**
	 * Setter
	 * @param updateKey the unique key of a password reset instance
	 */
	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}
	
}
