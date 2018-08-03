package com.registration;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for {@link User}
 *
 */

public interface UserRepository extends CrudRepository<User, Long> {
	
	boolean existsByEmail(String email);
	
}
