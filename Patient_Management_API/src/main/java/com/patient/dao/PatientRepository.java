package com.patient.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	
	public List<Patient> findByDisease(String disease);
	public List<Patient> findByStatus(String status);

}
