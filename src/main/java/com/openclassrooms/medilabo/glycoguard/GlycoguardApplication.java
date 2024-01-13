package com.openclassrooms.medilabo.glycoguard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GlycoguardApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlycoguardApplication.class, args);
	}

}
