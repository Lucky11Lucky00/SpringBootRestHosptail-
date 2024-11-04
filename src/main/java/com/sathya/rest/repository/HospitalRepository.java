package com.sathya.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sathya.rest.model.Hospital;

@Repository
	public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	Optional<Hospital> existsBySpecialization(String specialization);
		@Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Hospital h WHERE h.specialization = :specialization")
		boolean findBySpecialization(String specialization);
		
		@Transactional
		void deleteBySpecialization(String specialization);
		
		
		}
		

