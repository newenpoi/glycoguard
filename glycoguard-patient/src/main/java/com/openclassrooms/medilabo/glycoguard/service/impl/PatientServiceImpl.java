package com.openclassrooms.medilabo.glycoguard.service.impl;

import java.text.MessageFormat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
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
	
	/**
	 * Récupère une page de patients.
	 * Les offsets et le tri sont définit dans l'objet withPage.
	 */
	@Override
	public Page<Patient> retrievePatients(@NonNull Pageable withPage) {
		return patientDao.findAll(withPage);
	}

	/**
	 * Ajoute un patient.
	 */
	@Override
	public Patient addPatient(@NonNull Patient newPatient) {
		return patientDao.save(newPatient);
	}

	/**
	 * Renvoie le patient demandé ou une exception personnalisé.
	 */
	@Override
	public Patient retrievePatient(@NonNull Long id) {
		// Vérifie la persistance de l'utilisateur spécifié par son identifiant.
		return patientDao.findById(id).orElseThrow(() -> new PatientNotFoundException(MessageFormat.format("Le patient numéro {0} est introuvable.", id)));
	}

	/**
	 * Mets à jour les informations personnelles du patient.
	 * Une fois créée, on ne peut plus toucher aux propriétés fondamentales c'est à dire nom, prénom et date de naissance.
	 * Les seules propriétés modifiables sont résidence et numéro de téléphone.
	 */
	@Override
	public Patient updatePatient(@NonNull Long id, Patient updatedPatient) {
		// Vérifie la persistance de l'utilisateur spécifié par son identifiant.
		Patient found = patientDao.findById(id).orElseThrow(() -> new PatientNotFoundException(MessageFormat.format("Le patient numéro {0} est introuvable.", id)));
		
		// Faire persister l'adresse avant de sauver le patient.
		// Ou utiliser l'annotation @OneToOne(cascade = CascadeType.PERSIST) sur la résidence.
		// Address updatedAddress = updatedPatient.getResidence();
		
		found.setResidence(updatedPatient.getResidence());
		found.setPhone(updatedPatient.getPhone());
		
		return patientDao.save(found);
	}

	/**
	 * Supprime un patient.
	 */
	@Override
	public void removePatient(@NonNull Long id) {
		patientDao.deleteById(id);
	}
	
}
