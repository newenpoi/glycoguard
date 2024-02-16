package com.openclassrooms.medilabo.glycoguardui.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class Patient {
	
	private Long id;
	
	@NotBlank
	private String forename;
	
	@NotBlank
	private String name;
	
	@Past
	private LocalDate dob;
	
	private String phone;
	
	@NotNull
	private Gender gender;
	
	private Address residence;
	
	private LocalDateTime created;
}
