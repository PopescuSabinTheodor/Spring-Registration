package com.registration;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface for generic CRUD operations on a repository for {@link Hash}
 *
 */
public interface HashRepository extends CrudRepository<Hash, String>  {
	
	
	
}
