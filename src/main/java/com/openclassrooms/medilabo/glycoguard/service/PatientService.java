package com.openclassrooms.medilabo.glycoguard.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.openclassrooms.medilabo.glycoguard.business.Patient;

public interface PatientService {
	List<Patient> retrievePatients(Sort sort);
	Patient addPatient(Patient newPatient);
	Patient retrievePatient(Long id);
	Patient updatePatient(Long id, Patient updatedPatient);
	void removePatient(Long id);
}
