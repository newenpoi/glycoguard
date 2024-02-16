package com.openclassrooms.medilabo.glycoguardui.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.openclassrooms.medilabo.glycoguardui.beans.Note;
import com.openclassrooms.medilabo.glycoguardui.beans.Patient;
import com.openclassrooms.medilabo.glycoguardui.beans.RiskLevel;
import com.openclassrooms.medilabo.glycoguardui.proxies.GatewayProxy;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class PatientController {
	private final GatewayProxy gatewayProxy;

    @RequestMapping("/")
    public String recupererPatients(Model model) {
        // Appel au proxy avec les paramètres requis (numéro de page et tri).
        ResponseEntity<Page<Patient>> response = gatewayProxy.recupererPatients(0, "name");
        
        // Si response est un code no-content, response.getBody() vaut null et getContent() ne sera jamais appelé.
        
        // Ajout de l'attribut et affichage.
        model.addAttribute("patients", response.getBody().getContent());
        return "patient/patients";
    }
    
	@RequestMapping("/patients/{id}")
	public String fichePatient(@PathVariable Long id, Model model) {
		Patient patient = gatewayProxy.recupererPatient(id);
		List<Note> notes = gatewayProxy.recupererNotesPatient(id);
		RiskLevel evaluation = gatewayProxy.evaluate(id);

		model.addAttribute("patient", patient);
		model.addAttribute("notes", notes);
		model.addAttribute("evaluation", evaluation.getDescription());
		
		// Permet d'ajouer une nouvelle note dans la fiche.
		model.addAttribute("note", new Note());

		return "patient/patient.fiche";
	}
	
	@PostMapping("/patients/{id}/update")
	public String updatePatient(@Valid Patient patient, BindingResult result, Model model, RedirectAttributes rattr) {
		Patient updatedPatient = gatewayProxy.updatePatient(patient.getId(), patient);
		
		return "redirect:/patients/" + updatedPatient.getId();
	}
	
	@PostMapping("/notes/add")
	public String addNote(@Valid Note note, BindingResult result, Model model, RedirectAttributes rattr) {
	    // En cas d'erreur(s) de binding.
		if (result.hasErrors()) {
	        // Ajoute les erreurs parmis les attributs (liste des erreurs).
			rattr.addFlashAttribute("errors", result.getAllErrors());
			rattr.addAttribute("id", note.getPatId());
	        
			// Redirige vers la fiche.
			return "redirect:/patients/{id}";
	    }
		
		// Envoyer les données au gateway.
		gatewayProxy.ajouterNote(note);
		
		return "redirect:/patients/" + note.getPatId();
	}
}
