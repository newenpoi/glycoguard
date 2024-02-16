package com.openclassrooms.medilabo.glycoguardeval.beans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Note {
	// La création de l'identifiant sera géré par MongoDB.
	// Dans la mise à jour d'une note, l'identifiant est géré en @RequestParam.
	private String id;
	
	@NotNull
	private Long patId;
	
	@NotBlank
	private String patient;
	
	@NotBlank
	private String note;
}