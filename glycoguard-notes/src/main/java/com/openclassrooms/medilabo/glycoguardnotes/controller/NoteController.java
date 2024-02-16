package com.openclassrooms.medilabo.glycoguardnotes.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medilabo.glycoguardnotes.business.Note;
import com.openclassrooms.medilabo.glycoguardnotes.service.NoteService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class NoteController {
	
	private final NoteService noteService;
	
	/**
	 * Récupère une liste de notes d'un patient spécifié par son identifiant unique.
	 * @param id
	 * @return Notes ou Rien
	 */
	@GetMapping("/notes/{id}")
	public ResponseEntity<List<Note>> getNotesPatient(@PathVariable Long id) {
		List<Note> notes = noteService.recupererNotesPatient(id);
		
		// On renvoie explicitement cette réponse au client en cas de page vide.
		if (notes.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(notes);
	}
	
	/**
	 * Ajoute une note pour un patient.
	 * Notre identifiant unique du patient se trouve dans la propriété patId de la note.
	 * @param newNote
	 * @return Note ou Rien
	 */
	@PostMapping("/notes/add")
	public ResponseEntity<Note> addNote(@Valid @RequestBody Note newNote) {
		Note note = noteService.ajouterNote(newNote);
	    
	    // Dans le cas où aucune note n'a été créé.
	    if (note == null) return ResponseEntity.noContent().build();
	    
	    return ResponseEntity.status(HttpStatus.CREATED).body(note);
	}
}
