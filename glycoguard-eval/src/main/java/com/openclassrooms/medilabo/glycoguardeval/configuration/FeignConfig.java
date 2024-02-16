package com.openclassrooms.medilabo.glycoguardeval.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfig {
	
	/**
	 * Il s'agit du header d'authentification qu'on envoi par défaut pour l'auth. en mémoire de spring security.
	 * @return
	 */
	@Bean
	public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor()
	{
		return new BasicAuthRequestInterceptor("newenpoi", "azerty");
	}
}
