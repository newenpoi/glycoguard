package com.openclassrooms.medilabo.glycoguard.business;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String forename;
	
	@NotBlank
	private String name;
	
	@Past
	private LocalDate dob;
	
	@Column(nullable = true)
	private String phone;
	
	@NotNull
	private Gender gender;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Address residence;
	
	@NotNull
	private LocalDateTime created;
	
	public Patient() {
		this.created = LocalDateTime.now();
	}
	
	public Patient(String forename, String name, LocalDate dob, Gender gender) {
		this();
		
		this.forename = forename;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
	}
}
