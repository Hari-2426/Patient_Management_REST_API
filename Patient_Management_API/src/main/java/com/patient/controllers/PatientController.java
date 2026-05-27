package com.patient.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dto.PatientRequestDTO;
import com.patient.dto.PatientResponseDTO;
import com.patient.dto.PatientUpdateRequestDTO;
import com.patient.services.PatientServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	
	private static final Logger logger=LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	PatientServiceImpl patientService;
	
	
	@PostMapping("/addPatient")
	public PatientResponseDTO addNewPatientDetails(@Valid @RequestBody PatientRequestDTO addNewPatient)
	{
		logger.debug("New Patient Details is adding");
		logger.debug("Going to service layer");
		return patientService.addNewPatient(addNewPatient);
		
	}
	
	
	@GetMapping("/allPatients")
	public List<PatientResponseDTO> getAllPatients()
	{
		logger.debug("Started retriving all patients details");
		logger.info("Details Retrived from service layer");
		return patientService.getAllPatients();
	}
	
	@GetMapping("/getPatientById/{patientId}")
	public PatientResponseDTO getPatientById(@PathVariable int patientId)
	{
		logger.debug("Fetch Patient Details using Patient Id");
		logger.info("Fetch Patient Details from Patient Id  "+patientId);
		return patientService.getPatientById(patientId);
	}
	
	
	@GetMapping("/getPatientByDisease")
	public List<PatientResponseDTO> getPatientByDisease(@RequestParam(name = "byDisease") String disease)
	{
		logger.debug("Fetch Patients Details from Disease");
		logger.info("Fetch Patients Details from Disease "+disease);
		return patientService.getPatientByDisease(disease);
	}
	
	@PutMapping("/updatePatientDetails/{patientId}")
	public PatientResponseDTO updatePatientDetails(@PathVariable int patientId,@RequestBody PatientUpdateRequestDTO updateDetails)
	{
		logger.debug("Updating Patient Details based on Patient Id with user details");
		logger.info("Updating Patient Details based on Patient Id"+patientId+" with update details");
		return patientService.updatePatientDetails(patientId, updateDetails);
	}
	
	@DeleteMapping("/deletePatient/{patientId}")
	public String deletePatient(@PathVariable int patientId)
	{
		logger.debug("Deleting Patient Details...");
		logger.info("Deleted Patient By Id : "+patientId);
		return patientService.deletePatient(patientId);
	}
	
	@GetMapping("/getPatientsByStatus/{status}")
	public List<PatientResponseDTO> getPatientsByStatus(@PathVariable String status)
	{
		logger.debug("Retriving Patients Details by using status");
		logger.info("Retriving Patients Details by using status "+status);
		return patientService.getPatientsByStatus(status);
	}

}
