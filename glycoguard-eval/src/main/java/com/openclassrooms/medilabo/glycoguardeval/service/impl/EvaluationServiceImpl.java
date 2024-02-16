package com.openclassrooms.medilabo.glycoguardeval.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.medilabo.glycoguardeval.beans.Gender;
import com.openclassrooms.medilabo.glycoguardeval.beans.Note;
import com.openclassrooms.medilabo.glycoguardeval.beans.Patient;
import com.openclassrooms.medilabo.glycoguardeval.beans.RiskLevel;
import com.openclassrooms.medilabo.glycoguardeval.beans.Terminology;
import com.openclassrooms.medilabo.glycoguardeval.service.EvaluationService;
import com.openclassrooms.medilabo.glycoguardeval.utils.CustomDateUtils;

@Service
public class EvaluationServiceImpl implements EvaluationService {

	/**
	 * Bon ok ça a pas l'air super ergonomique mais le résultat est là.
	 * On pourrait réfléchir à une approche basé sur une échelle et une seconde méthode qui
	 * calculerait le risque.
	 * Accessoiremment utiliser un switch si possible...
	 */
	@Override
	public RiskLevel evaluate(Patient p, List<Note> notes) {
		// Récupérer l'âge du patient.
		int age = CustomDateUtils.calculateAge(p.getDob());
		Gender sex = p.getGender();
		
		// Le nombre de terminologies trouvées pour ce patient.
		int matches = 0;
		
		// Pour chaque notes vérifier la présence des termes déclencheurs (terminologie).
		for (Note note : notes) {
			// Parcours les terminologies.
			for (Terminology terminology : Terminology.values()) {
				// Si la note contient cette terminologie.
				if (note.getNote().toLowerCase().contains(terminology.getTerm().toLowerCase())) matches++;
			}
		}
		
		RiskLevel riskLevel = RiskLevel.NONE;
		
		// Si le patient a entre deux et cinq terminologies et a plus de trente ans.
		if (matches >= 2 && matches < 5 && age >= 30) riskLevel = RiskLevel.BORDERLINE;
		
		// Si le patient est un homme en dessous de trente ans avec au moins trois terminologies.
		if (sex == Gender.MASCULINE && (age < 30 && matches >= 3)) riskLevel = RiskLevel.INDANGER;
		
		// Si le patient est une femme en dessous de trente ans avec au moins quatre terminologies.
		if (sex == Gender.FEMININE && (age < 30 && matches >= 4)) riskLevel = RiskLevel.INDANGER;
		
		// Au dessus de trente ans et au moins six terminologies.
		if (age >= 30 && matches >= 6) riskLevel = RiskLevel.INDANGER;
		
		// Si le patient est un homme en dessous de trente ans avec au moins cinq terminologies.
		if (sex == Gender.MASCULINE && (age < 30 && matches >= 5)) riskLevel = RiskLevel.EARLYONSET;
		
		// Si le patient est une femme en dessous de trente ans avec au moins sept terminologies.
		if (sex == Gender.FEMININE && (age < 30 && matches >= 7)) riskLevel = RiskLevel.EARLYONSET;
		
		// Si le patient est une femme en dessous de trente ans avec au moins sept terminologies.
		if (age >= 30 && matches >= 8) riskLevel = RiskLevel.EARLYONSET;
		
		return (riskLevel);
	}

}
