package com.registration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Hash entity model
 *
 */
@Entity(name = "hash")
public class Hash  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer hashId;
	String hashValue;
	Long userId;
	public String getHashValue() {
		return hashValue;
	}
	public void setHashValue(String hashValue) {
		this.hashValue = hashValue;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
