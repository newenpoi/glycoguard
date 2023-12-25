package com.openclassrooms.medilabo.glycoguard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openclassrooms.medilabo.glycoguard.business.Address;

public interface AddressDao extends JpaRepository<Address, Long> {

}
