package com.openclassrooms.medilabo.glycoguardui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Dans le cas d'une ressource non trouvée.
 * @author newenpoi
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2L;
	
    public NotFoundException(String message) {
        super(message);
    }
}
