package com.openclassrooms.medilabo.glycoguard.exception;

/**
 * Exception personnalisée lorsqu'un patient n'existe pas.
 * @author lamme
 *
 */
public class PatientNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	public PatientNotFoundException(String message) {
        super(message);
    }
}
