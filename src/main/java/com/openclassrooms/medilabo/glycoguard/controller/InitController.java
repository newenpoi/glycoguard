package com.openclassrooms.medilabo.glycoguard.controller;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.openclassrooms.medilabo.glycoguard.business.Address;
import com.openclassrooms.medilabo.glycoguard.dao.AddressDao;

import lombok.AllArgsConstructor;

@Component
@Order(1)
@AllArgsConstructor
public class InitController implements CommandLineRunner {
	
	// Le nombre d'adresses fictives à créer.
	private final static long NB_ADDRESS = 100;
	
	private final static Faker faker = new Faker(new Locale("fr"));
	
	private final AddressDao addressDao;
	
	public void run(String... args) throws Exception {
		// Si aucune adresse enregistrée on en crée des fictives.
		if (addressDao.count() == 0) addAddresses();
		
		// Si aucun patient enregistré on en créé des fictifs.
		// ...
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
}
