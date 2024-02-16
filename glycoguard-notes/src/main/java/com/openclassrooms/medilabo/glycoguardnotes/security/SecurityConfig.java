package com.openclassrooms.medilabo.glycoguardnotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
	/**
	 * L'encodeur pour nos mots de passe.
	 * @return
	 */
	@Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
	/**
	 * Depuis 6.1 dans le cadre d'une authentification basique.
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> {
			authorize.anyRequest().authenticated();
		}).httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	/**
	 * Creation d'une authentification dit en mémoire avec coordonnées définies.
	 * @return
	 */
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails admin = User.builder()
                .username("newenpoi")
                .password(passwordEncoder().encode("azerty"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
	
    /**
     * Ignore le filtre sécurité pour les requêtes OpenAPI (documentation).
     * @return
     */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**");
	}
}
