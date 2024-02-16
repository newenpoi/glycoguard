package com.openclassrooms.medilabo.glycoguard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import com.openclassrooms.medilabo.glycoguard.business.Patient;

public interface PatientService {
	Page<Patient> retrievePatients(@NonNull Pageable withPage);
	Patient addPatient(@NonNull Patient newPatient);
	Patient retrievePatient(@NonNull Long id);
	Patient updatePatient(@NonNull Long id, Patient updatedPatient);
	void removePatient(@NonNull Long id);
}
