package com.patient.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.dao.PatientRepository;
import com.patient.dto.PatientRequestDTO;
import com.patient.dto.PatientResponseDTO;
import com.patient.dto.PatientUpdateRequestDTO;
import com.patient.exceptions.PatientNotFoundException;
import com.patient.model.Patient;
import com.patient.services.interfaces.PatientServiceInterface;

@Service
public class PatientServiceImpl implements PatientServiceInterface {

	
	@Autowired
	PatientRepository patientRepo;
	
	private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
	

	
	@Override
	public PatientResponseDTO addNewPatient(PatientRequestDTO addPatient) {
		
		Patient patient=new Patient();
		patient.setFirstName(addPatient.getFirstName());
		patient.setLastName(addPatient.getLastName());
		patient.setPatientAge(addPatient.getPatientAge());
		patient.setGender(addPatient.getGender());
		patient.setEmail(addPatient.getEmail());
		patient.setPhoneNumber(addPatient.getPhoneNumber());
		patient.setBloodGroup(addPatient.getBloodGroup());
		patient.setAddress(addPatient.getAddress());
		patient.setDisease(addPatient.getDisease());
		patient.setAdmittedOn(LocalDateTime.now());
		patient.setStatus("Admitted");
		Patient saveNewPatient = patientRepo.save(patient);
		
		PatientResponseDTO patientResponse=new PatientResponseDTO();
		
		BeanUtils.copyProperties(saveNewPatient, patientResponse);
		log.info("Registering new patient: {} {}", addPatient.getFirstName(), addPatient.getLastName());
		return patientResponse;
	}
	
	
	@Override
	public List<PatientResponseDTO> getAllPatients() {
		List<Patient> allPatients = patientRepo.findAll();
		List<PatientResponseDTO> responseList=new ArrayList<PatientResponseDTO>();
		for(Patient patient:allPatients)
		{
			PatientResponseDTO response=new PatientResponseDTO();
			BeanUtils.copyProperties(patient, response);
			responseList.add(response);
		}
		return responseList;
	}


	@Override
	public PatientResponseDTO getPatientById(int patientId) {
		
		Patient patient = patientRepo.findById(patientId).orElseThrow(()->new PatientNotFoundException("Patient Id : "+patientId+" Not Found"));
		PatientResponseDTO patientResponse=new PatientResponseDTO();
		BeanUtils.copyProperties(patient, patientResponse);
		log.debug("Fetching patient with ID: {}", patientId);
		
		
		return patientResponse;
	}


	@Override
	public List<PatientResponseDTO> getPatientByDisease(String disease) {
		
		
		List<Patient> byDisease = patientRepo.findByDisease(disease);
		List<PatientResponseDTO> responseList=new ArrayList<PatientResponseDTO>();
		for(Patient patient:byDisease)
		{
			PatientResponseDTO response = new PatientResponseDTO();
			BeanUtils.copyProperties(patient, response);
			responseList.add(response);
		}
		
		return responseList;
	}


	@Override
	public PatientResponseDTO updatePatientDetails(
	        int patientId,
	        PatientUpdateRequestDTO updateDetails) {

	    log.info("Updating patient with ID: {}", patientId);

	    Patient patient = patientRepo.findById(patientId)
	            .orElseThrow(() ->
	                    new PatientNotFoundException(
	                            "Patient Id : " + patientId + " Not Found"));

	    if(updateDetails.getAddress() != null) {
	        patient.setAddress(updateDetails.getAddress());
	    }

	    if(updateDetails.getDisease() != null) {
	        patient.setDisease(updateDetails.getDisease());
	    }

	    if(updateDetails.getAdmittedOn() != null) {
	        patient.setAdmittedOn(updateDetails.getAdmittedOn());
	    }

	    if(updateDetails.getStatus() != null) {
	        patient.setStatus(updateDetails.getStatus());
	    }

	    Patient savePatient = patientRepo.save(patient);

	    log.info("Patient updated successfully with ID: {}", patientId);

	    PatientResponseDTO response =
	            new PatientResponseDTO();

	    BeanUtils.copyProperties(savePatient, response);

	    return response;
	}


	@Override
	public String deletePatient(int patientId) {
		Patient patient = patientRepo.findById(patientId).orElseThrow(()->new PatientNotFoundException("Patient Id : "+patientId+" Not Found"));
		
		log.info("Deleting patient with ID: {}", patientId);
		patientRepo.delete(patient);
		return "Patient Deleted Successfully!!!! "+patient.getPatientId();
	}


	@Override
	public List<PatientResponseDTO> getPatientsByStatus(String status) {
		List<Patient> byStatus = patientRepo.findByStatus(status);
		List<PatientResponseDTO> responseList=new ArrayList<PatientResponseDTO>();
		for(Patient patient:byStatus)
		{
			PatientResponseDTO response = new PatientResponseDTO();
			BeanUtils.copyProperties(patient, response);
			responseList.add(response);
		}
		return responseList;
	}



}
