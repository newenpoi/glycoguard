package com.openclassrooms.medilabo.glycoguard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.medilabo.glycoguard.business.Address;

/**
 * Permets de générer des requêtes JPA pour notre entité Adress.
 * @author lamme
 *
 */
public interface AddressDao extends JpaRepository<Address, Long> {

}
