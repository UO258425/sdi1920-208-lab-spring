package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniovi.entities.Mark;
import com.uniovi.entities.User;

public interface MarksRepository extends CrudRepository<Mark, Long> {
	
	@Modifying
	@Transactional
	@Query("UPDATE Mark SET resend = ?1 WHERE id = ?2")
	void updateResend(Boolean resend, Long id);
	
	@Query("Select r from Mark r where r.user = ?1 order by r.id asc")
	Page<Mark> findAllByUser(Pageable pageable, User user);
	
	Page<Mark> findAll(Pageable pageable);
	
	@Query("SELECT r FROM Mark r WHERE (LOWER(r.description) LIKE LOWER(?1) or LOWER(r.user.name) LIKE LOWER(?1))")
	Page<Mark> searchByDescriptionAndName(Pageable pageable, String searchtext);
	
	@Query("SELECT r FROM Mark r WHERE (LOWER(r.description) LIKE LOWER(?1) or LOWER(r.user.name) LIKE LOWER(?1)) AND r.user = ?2")
	Page<Mark> searchByDescriptionNameAndUser(Pageable pageable, String searchtext, User user);
	


}
