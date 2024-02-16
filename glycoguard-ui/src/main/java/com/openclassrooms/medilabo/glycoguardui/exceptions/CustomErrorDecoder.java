package com.openclassrooms.medilabo.glycoguardui.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultErrorDecoder = new Default();
	
	/**
	 * Permet de gérer quelques exceptions communes.
	 */
	@Override
	public Exception decode(String invoker, Response response) {
		
		if (response.status() == 404) {
			return new NotFoundException(String.format("La ressource demandée est introuvable (%s).", response.status()));
		}
		
		if (response.status() >= 400 && response.status() <= 499) {
			return new BadRequestException(String.format("La requête n'a pu aboutir (%s).", response.status()));
		}
		
		if (response.status() >= 500 && response.status() <= 599) {
			return new CustomServerException(String.format("Le serveur n'a pas pu traiter la requête (%s).", response.status()));
		}
		
		return defaultErrorDecoder.decode(invoker, response);
	}
}
