package com.registration;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	
	public UpdateKey(String email) {
		super();
		this.email = email;
	}
	
	private String generateKey() {
		long timeStamp = System.currentTimeMillis();
		Random rand = new Random();
		return String.valueOf(timeStamp) + String.valueOf(rand.nextInt(100));
	}
	
	public String getUpdateKey() {
		return updateKey;
	}
	public String getEmail() {
		return email;
	}
	

}
