package com.openclassrooms.medilabo.glycoguard.controller;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.openclassrooms.medilabo.glycoguard.business.Address;
import com.openclassrooms.medilabo.glycoguard.business.Gender;
import com.openclassrooms.medilabo.glycoguard.business.Patient;
import com.openclassrooms.medilabo.glycoguard.dao.AddressDao;
import com.openclassrooms.medilabo.glycoguard.dao.PatientDao;

import lombok.AllArgsConstructor;

@Component
@Order(1)
@AllArgsConstructor
public class InitController implements CommandLineRunner {
	
	// Le nombre d'adresses fictives à créer.
	private final static long NB_ADDRESS = 100;
	
	// Initialise le module faker avec des données françaises.
	private final static Faker faker = new Faker(new Locale("fr"));
	
	private final AddressDao addressDao;
	private final PatientDao patientDao;
	
	public void run(String... args) throws Exception {
		// Si aucune adresse enregistrée on en crée des fictives.
		if (addressDao.count() == 0) addAddresses();
		
		// Si aucun patient enregistré on en créé des fictifs.
		if (patientDao.count() == 0) addPatients();
	}

	public void addAddresses() {
		// Créer des données fictives (des adresses en France selon la classe métier) avec Faker.
	    for (int i = 0; i < NB_ADDRESS; i++) {
	        Address address = new Address();
	        
	        address.setNumber(faker.address().buildingNumber());
	        address.setStreet(faker.address().streetName());
	        address.setZip("04777");
	        address.setCity("Glycopolis");
	        address.setCountry("France");
	        
	        addressDao.save(address);
	    }
	}
	
	public void addPatients() {
		patientDao.save(new Patient("Test", "TestNone", LocalDate.of(1966, 12, 31), Gender.FEMININE));
		patientDao.save(new Patient("Test", "TestBorderline", LocalDate.of(1945, 06, 24), Gender.MASCULINE));
		patientDao.save(new Patient("Test", "TestInDanger", LocalDate.of(2004, 06, 18), Gender.MASCULINE));
		patientDao.save(new Patient("Test", "TestEarlyOnset", LocalDate.of(2002, 06, 28), Gender.FEMININE));
	}
}
