package com.openclassrooms.medilabo.glycoguardui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Dans le cas d'une erreur serveur.
 * @author newenpoi
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomServerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public CustomServerException(String message) {
        super(message);
    }
}
