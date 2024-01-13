package com.openclassrooms.medilabo.glycoguard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(new AntPathRequestMatcher("/"),
								new AntPathRequestMatcher("/actuator/refresh"),
								new AntPathRequestMatcher("/patients"),
								new AntPathRequestMatcher("/patients/**"),
								new AntPathRequestMatcher("/swagger-ui/**"))
						.permitAll().requestMatchers(new AntPathRequestMatcher("/admin/**")).authenticated())
				.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");
	}
}
