package com.openclassrooms.medilabo.glycoguardgateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
	
	/**
	 * Depuis 6.1 dans le cadre d'une authentification basique.
	 * @param http
	 * @return
	 */
	@Bean
	public SecurityWebFilterChain springSecurityFilterChainSample(ServerHttpSecurity http) {
		
		return http.csrf(csrf -> csrf.disable()).authorizeExchange(exchanges -> exchanges
            .pathMatchers("/patients/**", "/notes/**", "/evaluator/**").authenticated()
            .anyExchange().permitAll())
			.httpBasic(basic -> basic.authenticationManager(reactiveAuthenticationManager()))
			.build();
	}
    
	/**
	 * En mémoire.
	 * @return
	 */
    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager(){
        return new UserDetailsRepositoryReactiveAuthenticationManager(getInMemoryUserDetails());
    }

    /**
     * Acceptable en développement.
     * En production le mot de passe est encodé extérieurement.
     * @return
     */
    @Bean
    public MapReactiveUserDetailsService getInMemoryUserDetails() {
        UserDetails admin = User.withDefaultPasswordEncoder().username("newenpoi").password("azerty")
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(admin);
    }
}
