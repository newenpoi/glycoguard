package com.openclassrooms.medilabo.glycoguardui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.medilabo.glycoguardui.beans.Note;
import com.openclassrooms.medilabo.glycoguardui.beans.Patient;
import com.openclassrooms.medilabo.glycoguardui.beans.RiskLevel;

@FeignClient(name = "microservice-gateway", url = "glycoguard-gateway:9103")
public interface GatewayProxy {
	
	/**
	 * Demande une page de patients avec numéro de page et tri.
	 * @param numPage
	 * @param sort
	 * @return
	 */
    @GetMapping("/patients")
    ResponseEntity<Page<Patient>> recupererPatients(@RequestParam("page") int numPage, @RequestParam("sort") String sort);

    /**
     * Demande un patient par son identifiant.
     * @param idPatient
     * @return
     */
    @GetMapping("/patients/{id}")
    Patient recupererPatient(@PathVariable("id") Long idPatient);
    
    /**
     * Mets à jour un patient en fournissant l'identifiant et le corps du modèle.
     * @param idPatient
     * @param patient
     * @return
     */
    @PutMapping("/patients/{id}/update")
    Patient updatePatient(@PathVariable("id") Long idPatient, @RequestBody Patient patient);
    
    /**
     * Récupère les notes d'un patient.
     * @param numPatient
     * @return
     */
	@GetMapping("/notes/{id}")
    List<Note> recupererNotesPatient(@RequestParam("id") Long numPatient);
	
	/**
	 * Ajoute une note pour un patient donné (dans la note avec patId).
	 * @param note
	 * @return
	 */
	@PostMapping("/notes/add")
	Note ajouterNote(@RequestBody Note note);
	
	/**
	 * Demande une évaluation de risque du patient spécifié par son identifiant.
	 * @param idPatient
	 * @return
	 */
	@GetMapping("/evaluator/{id}")
	RiskLevel evaluate(@PathVariable("id") Long idPatient);
}
