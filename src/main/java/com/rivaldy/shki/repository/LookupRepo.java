package com.rivaldy.shki.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rivaldy.shki.domain.Lookup;

@Repository
public interface LookupRepo extends JpaRepository<Lookup, String>{

	List<Lookup> findByType(String type);
	List<Lookup> findByCode(String code);
	Lookup findByTypeAndCode(String type, String code);
}
