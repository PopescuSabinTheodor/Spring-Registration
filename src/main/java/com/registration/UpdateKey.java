package com.registration;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Model class for the UpdateKey entity
 *
 */
@Entity(name = "updateKey")
public class UpdateKey {
	/**
	 * The update key
	 */
	@Id
	private String updateKey = generateKey();
	/**
	 * The user's email for which the key was generated
	 */
	private String email;
	/**
	 * No arguments constructor used for initialization from database
	 */
	protected UpdateKey() {
	}
	/**
	 * Constructor
	 * @param email user's email address
	 */
	public UpdateKey(String email) {
		super();
		this.email = email;
	}
	/**
	 * Generates a random string based on the current timestamp and a random value between 0 and 99
	 * @return a random string used as update key
	 */
	private String generateKey() {
		long timeStamp = System.currentTimeMillis();
		Random rand = new Random();
		return String.valueOf(timeStamp) + String.valueOf(rand.nextInt(100));
	}
	/**
	 * Getter
	 * @return the user's unique key
	 */
	public String getUpdateKey() {
		return updateKey;
	}
	/**
	 * Getter
	 * @return the user's email address
	 */
	public String getEmail() {
		return email;
	}
	

}
