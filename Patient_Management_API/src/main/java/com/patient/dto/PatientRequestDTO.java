package com.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDTO {
	
	@NotBlank(message = "First Name is required")
	private String firstName;
	@NotBlank(message = "Last name is required")
	private String lastName;
	@Min(value = 1, message = "Age must be at least 1")
	@Max(value = 120, message = "Age must not exceed 120")
	private int patientAge;
	@NotBlank(message = "Gender is required")
	private String gender;
	@NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
	private String email;
	
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone must be exactly 10 digits")
	private String phoneNumber;
	@NotBlank(message = "Blood group is required")
	private String bloodGroup;
	private String address;
	
	@NotBlank(message = "Disease is required")
	private String disease;
	

}
