package fr.redkissifrott.abernathyReport.proxies;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.redkissifrott.abernathyReport.model.Patient;

@FeignClient(name = "abernathyPatient", url = "${patientProxy}")
public interface PatientProxy {

	@GetMapping(value = "patient/{id}")
	public Optional<Patient> getPatient(@PathVariable("id") Integer id);

}
