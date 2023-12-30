package com.openclassrooms.medilabo.glycoguard.controller;

import java.util.List;

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
import com.openclassrooms.medilabo.glycoguard.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PatientController {
	
	private final PatientService patientService;
	
	@Operation(description = "Récupère la liste des patients.")
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getPatients(@RequestParam(name = "sort", defaultValue = "name") String sort) {
		List<Patient> patients = patientService.retrievePatients(Sort.by(sort));
		
		// On propose explicitement cette réponse.
		if (patients.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(patients);
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
		Patient requestedPatient = patientService.retrievePatient(id);
		
		return ResponseEntity.ok(requestedPatient);
	}
	
	@PutMapping("/patient/{id}/update")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient detailsPatient) {
	    Patient updatedPatient = patientService.updatePatient(id, detailsPatient);
	    
	    return ResponseEntity.ok(updatedPatient);
	}
	
	@PostMapping("/patient/add")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient newPatient) {
	    Patient createdPatient = patientService.addPatient(newPatient);
	    
	    // Dans le cas où aucun patient n'a été créé.
	    if (createdPatient == null) return ResponseEntity.noContent().build();
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
	}
	
	@DeleteMapping("/patient/{id}/delete")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {
		patientService.removePatient(id);
		
		return ResponseEntity.ok("Patient supprimé avec succès.");
	}
}
