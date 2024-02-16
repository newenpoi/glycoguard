package com.openclassrooms.medilabo.glycoguardnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GlycoguardNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlycoguardNotesApplication.class, args);
	}

}
