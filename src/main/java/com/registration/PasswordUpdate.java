package com.registration;
/**
 * Class used to evidentiate if the user's password has or hasn't been updated
 *
 */
public class PasswordUpdate {
	/**
	 * Used to represent the result of trying to update a user's password
	 */
	private boolean passwordUpdated = false;
	/**
	 * Getter
	 * @return result of the password updating method
	 */
	public boolean isPasswordUpdated() {
		return passwordUpdated;
	}
	/**
	 * Setter
	 * @param passwordUpdated result of the password updating method
	 */
	public void setPasswordUpdated(boolean passwordUpdated) {
		this.passwordUpdated = passwordUpdated;
	}
	
	
}
