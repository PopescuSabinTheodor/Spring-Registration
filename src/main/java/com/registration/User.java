package com.registration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * User entity model
 *
 */
@Entity(name = "users")
public class User {
	/**
	 * User's unique identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	/**
	 * User's first name
	 */
	private String firstName;
	/**
	 * User's last name
	 */
	private String lastName;
	/**
	 * User's email address
	 */
	private String email;
	/**
	 * User's password
	 */
	private String password;
	/**
	 * User's status  approved/unapproved
	 */
	private String status;
	/**
	 * User's role
	 */
	private String role;
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
	 * @return the user's status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * Setter
	 * @param status the user's status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Getter
	 * @return the user's role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * Setter
	 * @param role the user's role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * Getter
	 * @return the user's id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * Setter
	 * @param the user's id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * Overridden hashCode method for User object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	
	

}
