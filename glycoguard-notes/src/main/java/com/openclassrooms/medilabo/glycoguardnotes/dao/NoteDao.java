package com.openclassrooms.medilabo.glycoguardnotes.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.openclassrooms.medilabo.glycoguardnotes.business.Note;

public interface NoteDao extends MongoRepository<Note, String> {
	List<Note> findByPatient(String name);
	List<Note> findByPatId(Long patId);
}
