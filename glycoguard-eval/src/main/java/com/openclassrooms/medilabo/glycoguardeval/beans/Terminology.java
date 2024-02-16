package com.openclassrooms.medilabo.glycoguardeval.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Ce qu'on aurait pu faire différemment :
 * Créer une table pour les terminologies et non une énumération.
 * @author newenpoi
 *
 */
@AllArgsConstructor
@Getter
public enum Terminology {
    HEMOGLOBINE_A1C("Hémoglobine A1C"),
    MICROALBUMINE("Microalbumine"),
    TAILLE("Taille"),
    POIDS("Poids"),
    FUMEUR("Fumeur"),
    FUMEUSE("Fumeuse"),
    ANORMAL("Anormal"),
    CHOLESTEROL("Cholestérol"),
    VERTIGES("Vertiges"),
    RECHUTE("Rechute"),
    REACTION("Réaction"),
    ANTICORPS("Anticorps");
	
	private final String term;
}
