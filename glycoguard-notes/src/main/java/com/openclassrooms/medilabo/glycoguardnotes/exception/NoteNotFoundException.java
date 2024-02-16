package com.openclassrooms.medilabo.glycoguardnotes.exception;

/**
 * Exception personnalisée lorsqu'une note n'existe pas.
 * @author lamme
 *
 */
public class NoteNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;
	
	public NoteNotFoundException(String message) {
        super(message);
    }
}
