package com.openclassrooms.medilabo.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Microservice pour l'équilibrage de charges et la découverte.
 * @author newenpoi
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class ServeurEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeurEurekaApplication.class, args);
	}

}
