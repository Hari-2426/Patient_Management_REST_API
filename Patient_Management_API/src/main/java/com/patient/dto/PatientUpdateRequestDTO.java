package com.patient.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateRequestDTO {
	
	private String firstName;
	private String lastName;
	private Integer patientAge;
	private String gender;
	private String email;
	private String phoneNumber;
	private String bloodGroup;
	private String address;
	private String disease;

	private LocalDateTime admittedOn;
	
	private String status;

}
