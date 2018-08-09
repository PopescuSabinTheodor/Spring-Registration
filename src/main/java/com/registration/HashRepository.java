package com.registration;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Interface for generic CRUD operations on a repository for {@link Hash}
 *
 */
public interface HashRepository extends CrudRepository<Hash, String>  {
	
	
	
}
