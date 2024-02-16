package com.openclassrooms.medilabo.glycoguardgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GlycoguardGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlycoguardGatewayApplication.class, args);
	}

}
