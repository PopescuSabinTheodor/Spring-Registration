package com.registration;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HashRepository extends CrudRepository<Hash, Long>  {
	
	boolean existsByHashValue(String hashValue);
	
	void deleteByHashValue(String hashValue);
	
	@Modifying
	@Query("Select userId FROM hash WHERE hashValue = :hashValue")
	List<Long> findByHashValue(@Param("hashValue") String hashValue);
}
