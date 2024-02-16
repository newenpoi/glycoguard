package com.openclassrooms.medilabo.glycoguard.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medilabo.glycoguard.business.Patient;
import com.openclassrooms.medilabo.glycoguard.configuration.ApplicationPropertiesConfiguration;
import com.openclassrooms.medilabo.glycoguard.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PatientController {
	
	private final ApplicationPropertiesConfiguration appProperties;
	private final PatientService patientService;
	
	/**
	 * Récupère une page de patients en fonction du numéro de page et du tri.
	 * @param numPage
	 * @param sort
	 * @return
	 */
	@Operation(description = "Récupère la liste des patients.")
	@GetMapping("/patients")
	public ResponseEntity<Page<Patient>> getPatients(@RequestParam(name = "page", defaultValue = "0") int numPage, @RequestParam(name = "sort", defaultValue = "name") String sort) {
		// Produit un objet de demande de page avec les paramètres spécifiés.
		Pageable pageable = PageRequest.of(numPage, appProperties.getMaxPatients(), Sort.by(sort));
		
		// Produit une page de patients.
		Page<Patient> patients = patientService.retrievePatients(pageable);
		
		// On renvoie explicitement cette réponse au client en cas de page vide.
		if (patients.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(patients);
	}
	
	/**
	 * Prends en paramètre l'identifiant du patient dont on souhaite récupérer les infos.
	 * @param id
	 * @return
	 */
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
		Patient requestedPatient = patientService.retrievePatient(id);
		
		return ResponseEntity.ok(requestedPatient);
	}
	
	/**
	 * Mets à jour les détails d'un patient.
	 * @param id
	 * @param detailsPatient
	 * @return
	 */
	@PutMapping("/patients/{id}/update")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient detailsPatient) {
		Patient updatedPatient = patientService.updatePatient(id, detailsPatient);
	    
	    return ResponseEntity.ok(updatedPatient);
	}
	
	/**
	 * Ajoute un nouveau patient en base de données MySQL.
	 * @param newPatient
	 * @return
	 */
	@PostMapping("/patients/add")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient newPatient) {
	    Patient createdPatient = patientService.addPatient(newPatient);
	    
	    // Dans le cas où aucun patient n'a été créé.
	    if (createdPatient == null) return ResponseEntity.noContent().build();
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
	}
	
	/**
	 * Supprime un patient de la base de données MySQL.
	 * @param id
	 * @return
	 */
	@DeleteMapping("/patients/{id}/delete")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {
		patientService.removePatient(id);
		
		return ResponseEntity.ok("Patient supprimé avec succès.");
	}
}
