package com.registration;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for {@link User}
 *
 */

public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * Checks if the user with the corresponding email exists in the database
	 * @param email
	 * @return true if it does, false otherwise
	 */
	boolean existsByEmail(String email);
	/**
	 * Searches for a user with the corresponding email in the database
	 * @param email email address
	 * @return an {@link Optional} of type {@link User} 
	 */
	Optional<User> findOneByEmail(String email);
	
}
