package com.rivaldy.shki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rivaldy.shki.domain.Status;

@Repository
public interface StatusRepo extends JpaRepository<Status, String>{

	List<Status> findByType(String type);
	List<Status> findByCode(String code);
	Status findByTypeAndCode(String type, String code);
	@Query("SELECT o FROM Status o WHERE (o.code) LIKE CONCAT('%',UPPER(?1),'%') "
			+ " AND UPPER(o.name) LIKE CONCAT('%',UPPER(?2),'%')"
			+ " AND UPPER(o.description) LIKE CONCAT('%',UPPER(?3),'%')"
			+ " AND UPPER(o.type) LIKE CONCAT('%',UPPER(?4),'%')"
			+ " ORDER BY o.id")
	List<Status> findByFilter(String code, String name, String description, String type);
	
	@Query(value = "SELECT * FROM Status o WHERE 1=1 ", nativeQuery=true)
	List<Status> findByFilterTesting(String filter);
}
