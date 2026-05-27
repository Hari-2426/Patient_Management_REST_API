package com.patient.services.interfaces;

import java.util.List;

import com.patient.dto.PatientRequestDTO;
import com.patient.dto.PatientResponseDTO;
import com.patient.dto.PatientUpdateRequestDTO;

public interface PatientServiceInterface {
	
	public PatientResponseDTO addNewPatient(PatientRequestDTO addPatient);
	public List<PatientResponseDTO> getAllPatients();
	public PatientResponseDTO getPatientById(int patientId);
	public PatientResponseDTO updatePatientDetails(int patientId,PatientUpdateRequestDTO updateDetails);
	public List<PatientResponseDTO> getPatientByDisease(String disease);
	public String deletePatient(int patientId);
	public List<PatientResponseDTO> getPatientsByStatus(String status);
	

}
