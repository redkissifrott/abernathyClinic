package fr.redkissifrott.abernathyPatient.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.redkissifrott.abernathyPatient.exception.PatientAlreadyExists;
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
	 * @throws PatientAlreadyExists
	 */
	@PostMapping("/add")
	public Patient addPatient(@Valid @RequestBody Patient patient) throws PatientAlreadyExists {
		if (patientService.getPatientByFamilyAndGiven(patient.getFamily(), patient.getGiven()).isPresent()) {
			throw new PatientAlreadyExists("A patient already exist with this family and given : " + patient.getFamily()
					+ " " + patient.getGiven());
		}
		return patientService.savePatient(patient);
	}

	/**
	 * Read - get all patients in list
	 * 
	 * @return iterable patients
	 */
	@GetMapping(value = "/list")
	public Iterable<Patient> getPatients() {
		return patientService.getPatients();
	}

	/**
	 * Read - get a patient's info
	 * 
	 * @param patient id
	 * @return patient
	 */
	@GetMapping(value = "/{id}")
	public Optional<Patient> getPatient(@PathVariable("id") UUID id) {
		return patientService.getPatient(id);
	}

	/**
	 * Delete patient from DB
	 * 
	 * @param patient id
	 */
	@GetMapping(value = "/delete/{id}")
	public void deletePatient(@PathVariable("id") UUID id) {
		patientService.deletePatient(id);
	}
}
