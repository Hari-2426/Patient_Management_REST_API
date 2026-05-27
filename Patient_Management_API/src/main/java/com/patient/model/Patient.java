package com.patient.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Entity
@Table(name="patients")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@Min(value = 1)
	@Max(value=100)
	private int patientAge;
	
	private String gender;
	
	@Email
	private String email;
	
	private String phoneNumber;
	
	@NotNull
	private String bloodGroup;
	
	private String address;
	
	@NotNull
	private String disease;
	
	private LocalDateTime  admittedOn;
	
	private String status;
	
	
	public Patient(String firstName, String lastName, int patientAge, String gender, String email, String phoneNumber,
			String bloodGroup, String address, String disease, LocalDateTime  admittedOn, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.patientAge = patientAge;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.bloodGroup = bloodGroup;
		this.address = address;
		this.disease = disease;
		this.admittedOn = admittedOn;
		this.status = status;
	}
	
	
	

}
