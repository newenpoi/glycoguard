package com.openclassrooms.medilabo.glycoguard.service.impl;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.openclassrooms.medilabo.glycoguard.business.Patient;
import com.openclassrooms.medilabo.glycoguard.dao.PatientDao;
import com.openclassrooms.medilabo.glycoguard.exception.PatientNotFoundException;
import com.openclassrooms.medilabo.glycoguard.service.PatientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

	private final PatientDao patientDao;
	
	@Override
	public List<Patient> retrievePatients(Sort sort) {
		return patientDao.findAll(sort);
	}

	@Override
	public Patient addPatient(Patient newPatient) {
		return patientDao.save(newPatient);
	}

	/**
	 * Renvoie le patient demandé ou une exception personnalisé.
	 */
	@Override
	public Patient retrievePatient(Long id) {
		// Vérifie la persistance de l'utilisateur spécifié par son identifiant.
		return patientDao.findById(id).orElseThrow(() -> new PatientNotFoundException(MessageFormat.format("Le patient numéro {0} est introuvable.", id)));
	}

	/**
	 * Mets à jour les informations personnelles du patient.
	 * Une fois créée, on ne peut plus toucher aux propriétés fondamentales c'est à dire nom, prénom et date de naissance.
	 * Les seules propriétés modifiables sont résidence et numéro de téléphone.
	 */
	@Override
	public Patient updatePatient(Long id, Patient updatedPatient) {
		// Vérifie la persistance de l'utilisateur spécifié par son identifiant.
		Patient found = patientDao.findById(id).orElseThrow(() -> new PatientNotFoundException(MessageFormat.format("Le patient numéro {0} est introuvable.", id)));
		
		found.setResidence(updatedPatient.getResidence());
		found.setPhone(updatedPatient.getPhone());
		
		return patientDao.save(found);
	}

	@Override
	public void removePatient(Long id) {
		patientDao.deleteById(id);
	}
	
}
