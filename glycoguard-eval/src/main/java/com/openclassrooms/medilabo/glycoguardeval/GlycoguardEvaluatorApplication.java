package com.openclassrooms.medilabo.glycoguardeval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GlycoguardEvaluatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlycoguardEvaluatorApplication.class, args);
	}

}
