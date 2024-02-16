package com.openclassrooms.medilabo.glycoguardui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Dans le cas d'une requête mal formée ou invalide.
 * @author newenpoi
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 3L;

	public BadRequestException(String message) {
        super(message);
    }
}
