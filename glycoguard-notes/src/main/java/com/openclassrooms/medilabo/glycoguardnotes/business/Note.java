package com.openclassrooms.medilabo.glycoguardnotes.business;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "notes")
@Getter
@Setter
public class Note {
	@Id
	private String id;
	
	@NotNull
	private Long patId;
	
	@NotBlank
	private String patient;
	
	@NotBlank
	private String note;
}
