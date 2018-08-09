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
	
	public Hash(Long userId) {
		super();
		this.userId = userId;
	}
	
	private String generateHash() {
		long timeStamp = System.currentTimeMillis();
		Random rand = new Random();
		return String.valueOf(timeStamp) + String.valueOf(rand.nextInt(100));
	}
	
	public String getHashKey() {
		return hashKey;
	}
	public Long getUserId() {
		return userId;
	}
	
	
	
}
