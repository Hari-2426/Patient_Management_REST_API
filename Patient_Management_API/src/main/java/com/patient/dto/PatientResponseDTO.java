package com.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor	
public class PatientResponseDTO {
	
	private int patientId;
	private String firstName;
	private String lastName;
	private int patientAge;
	private String gender;
	private String email;
	private String phoneNumber;
	private String bloodGroup;
	private String address;
	private String disease;

	private LocalDateTime admittedOn;
	
	private String status;

}
