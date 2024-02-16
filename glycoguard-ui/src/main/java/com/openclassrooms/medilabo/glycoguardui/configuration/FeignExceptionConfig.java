package com.openclassrooms.medilabo.glycoguardui.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openclassrooms.medilabo.glycoguardui.exceptions.CustomErrorDecoder;

@Configuration
public class FeignExceptionConfig {

	/**
	 * On injecte notre decoder personnalisé.
	 * @return
	 */
	@Bean
	public CustomErrorDecoder mCustomErrorDecoder() {
		return new CustomErrorDecoder();
	}
}
