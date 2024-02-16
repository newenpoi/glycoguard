package com.openclassrooms.medilabo.glycoguardnotes.service;

import java.util.List;

import com.openclassrooms.medilabo.glycoguardnotes.business.Note;

public interface NoteService {
	List<Note> recupererNotesPatient(String name);
	List<Note> recupererNotesPatient(Long patId);
	Note ajouterNote(Note note);
}
