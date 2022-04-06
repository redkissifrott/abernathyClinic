package fr.redkissifrott.abernathyClient.proxies;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.redkissifrott.abernathyClient.model.Patient;

@FeignClient(name = "abernathyPatient", url = "${patientProxy}")
public interface PatientProxy {

	@GetMapping(value = "patient/list")
	public Iterable<Patient> getPatients();

	@PostMapping(value = "patient/add")
	public Patient addPatient(@RequestBody Patient patient);

	@GetMapping(value = "patient/{id}")
	public Optional<Patient> getPatient(@PathVariable("id") Integer id);

	@GetMapping(value = "patient/delete/{id}")
	public void deletePatient(@PathVariable("id") Integer id);

}
