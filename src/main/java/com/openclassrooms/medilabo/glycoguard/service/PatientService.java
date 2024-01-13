package com.openclassrooms.medilabo.glycoguard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.openclassrooms.medilabo.glycoguard.business.Patient;

public interface PatientService {
	Page<Patient> retrievePatients(Pageable withPage);
	Patient addPatient(Patient newPatient);
	Patient retrievePatient(Long id);
	Patient updatePatient(Long id, Patient updatedPatient);
	void removePatient(Long id);
}
