package com.openclassrooms.medilabo.glycoguardeval.service;

import java.util.List;

import com.openclassrooms.medilabo.glycoguardeval.beans.Note;
import com.openclassrooms.medilabo.glycoguardeval.beans.Patient;
import com.openclassrooms.medilabo.glycoguardeval.beans.RiskLevel;

public interface EvaluationService {
	RiskLevel evaluate(Patient p, List<Note> notes);
}
