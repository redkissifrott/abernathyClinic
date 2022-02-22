package fr.redkissifrott.abernathyPatient.controller;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.redkissifrott.abernathyPatient.model.Patient;
import fr.redkissifrott.abernathyPatient.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	private Logger logger;

	@Autowired
	PatientService patientService;

	/**
	 * Create - add a patient to DB
	 * 
	 * @param the patient to add
	 * @return the patient
	 */
	@PostMapping("/add")
	public Patient addPatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
		return patient;
	}

	@GetMapping(value = "/list")
	public Iterable<Patient> getPatients() {
		return patientService.getPatients();
	}

	@GetMapping(value = "/{id}")
	public Optional<Patient> getPatient(@PathVariable("id") UUID id) {
		return patientService.getPatient(id);
	}

	@GetMapping(value = "/delete/{id}")
	public void deletePatient(@PathVariable("id") UUID id) {
		patientService.deletePatient(id);
	}
}
