package com.openclassrooms.medilabo.glycoguard.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	/**
	 * Swagger est le prédecesseur d'OpenAPI.<br>
	 * Ce bean définit les informations de base lors de l'accès à la page de documentation de notre api.
	 * @return
	 */
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Glycoguard").description("La prévention avant le coma glycémique, telle est notre devise !").version("1.0"));
	}
}
