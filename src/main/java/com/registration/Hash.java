package com.registration;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Hash entity model
 *
 */
@Entity(name = "hash")
public class Hash  {
	/**
	 * The hash itself
	 */
	@Id
	private String hashKey = generateHash();
	/**
	 * The user's id for which the hash was generated
	 */
	private Long userId;
	/**
	 * No arguments constructor used for initialization from database
	 */
	protected Hash() {
	}
	/**
	 * Constructor 
	 * @param userId the user's id
	 */
	public Hash(Long userId) {
		super();
		this.userId = userId;
	}
	/**
	 * Generates a random string based on the current timestamp and a random value between 0 and 99
	 * @return a random string used as hash
	 */
	private String generateHash() {
		long timeStamp = System.currentTimeMillis();
		Random rand = new Random();
		return String.valueOf(timeStamp) + String.valueOf(rand.nextInt(100));
	}
	/**
	 * Getter
	 * @return hash
	 */
	public String getHashKey() {
		return hashKey;
	}
	/**
	 * Getter
	 * @return the user's id
	 */
	public Long getUserId() {
		return userId;
	}
	
	
	
}
