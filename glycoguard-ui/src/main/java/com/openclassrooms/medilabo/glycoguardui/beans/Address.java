package com.openclassrooms.medilabo.glycoguardui.beans;

import lombok.Data;

@Data
public class Address {
	
	private Long id;
	
	private String number;
	
	private String street;
	
	private String zip;
	
	private String city;
	
	private String country;
}
