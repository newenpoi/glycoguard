package com.openclassrooms.medilabo.glycoguardeval.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openclassrooms.medilabo.glycoguardeval.beans.Patient;

/**
 * Pour la communication avec le micro service patients.
 * @author newenpoi
 *
 */
@FeignClient(name = "microservice-patients", url = "glycoguard-patients:9111")
public interface PatientProxy {
    
	@GetMapping("/patients/{idPatient}")
    Patient recupererPatient(@PathVariable("idPatient") Long idPatient);
}
