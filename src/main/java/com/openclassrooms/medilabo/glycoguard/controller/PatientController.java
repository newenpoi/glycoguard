package com.openclassrooms.medilabo.glycoguard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medilabo.glycoguard.business.Patient;
import com.openclassrooms.medilabo.glycoguard.service.PatientService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class PatientController {
	
	private final PatientService patientService;
	
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getPatients() {
		List<Patient> patients = patientService.retrievePatients();
		
		// On propose explicitement cette réponse.
		if (patients.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(patients);
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
		Patient requestedPatient = patientService.retrievePatient(id);
		
		return ResponseEntity.ok(requestedPatient);
	}
	
	@GetMapping("/patient/{id}/update")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient detailsPatient) {
	    Patient updatedPatient = patientService.updatePatient(id, detailsPatient);
	    
	    return ResponseEntity.ok(updatedPatient);
	}
	
	@GetMapping("/patient/add")
	public ResponseEntity<Patient> addPatient(@RequestBody Patient newPatient) {
	    Patient createdPatient = patientService.addPatient(newPatient);
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
	}
}
