package com.openclassrooms.medilabo.glycoguardgateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

	/**
	 * Déclare les routes empruntés et détermine leur configuration.<br>
	 * Dans notre cas on les redirige vers le load balancer dont le nom est bien spécifié par celui de nos applications.
	 * @param builder
	 * @return
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("patients-service", r -> r.path("/patients/**").uri("lb://microservice-patients"))
				.route("notes-service", r -> r.path("/notes/**").uri("lb://microservice-notes"))
				.route("evaluation-service", r -> r.path("/evaluator/**").uri("lb://microservice-eval"))
				.build();
	}
}