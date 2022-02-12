package fr.redkissifrott.abernathyPatient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.redkissifrott.abernathyPatient.model.Patient;
import fr.redkissifrott.abernathyPatient.service.PatientService;

@RestController
@RequestMapping("patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	// @PostMapping(path = "/add", consumes = {
	// MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	// public ResponseEntity addPatient(Patient patient) {
	// patientService.savePatient(patient);
	// return ResponseEntity.ok(HttpStatus.OK);
	// }

	@PostMapping("/add")
	public Patient addPatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
		return patient;
	}
	void ResponseEntity() {
	}
}
