package com.registration;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends CrudRepository<User, Long> {
	
	boolean existsByEmail(String email);
	
	@Modifying
	@Query("UPDATE users user SET user.status = ?1 where user.id = ?2")
	void updateUser(String status, Integer id);
	
}
