package com.openclassrooms.medilabo.glycoguard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.medilabo.glycoguard.business.Patient;

/**
 * Permets de générer des requêtes JPA pour notre entité Patient.
 * @author lamme
 *
 */
public interface PatientDao extends JpaRepository<Patient, Long> {

}
