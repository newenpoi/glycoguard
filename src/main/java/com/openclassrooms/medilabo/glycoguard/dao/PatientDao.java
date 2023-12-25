package com.openclassrooms.medilabo.glycoguard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.medilabo.glycoguard.business.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
