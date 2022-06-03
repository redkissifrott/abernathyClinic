package fr.redkissifrott.abernathyPatient.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.redkissifrott.abernathyPatient.exception.CustomErrorMessage;
import fr.redkissifrott.abernathyPatient.exception.PatientAlreadyExists;
import fr.redkissifrott.abernathyPatient.exception.PatientNotFoundException;
import fr.redkissifrott.abernathyPatient.model.Patient;
import fr.redkissifrott.abernathyPatient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "PatientController", description = "Patient CRUD")
@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	PatientService patientService;

	/**
	 * Create - add a patient in DB
	 * 
	 * @param the patient to add
	 * @return the patient
	 * @throws PatientAlreadyExists
	 */
	@Operation(summary = "Add a patient", description = "Add patient send in body to DataBase")
	@ApiResponse(responseCode = "201", description = "Patient created", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "409", description = "Patient already exist with this family and given", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "422", description = "MethodArgumentNotValidException", content = @Content(mediaType = "application/json"))
	@PostMapping("/add")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient)
			throws PatientAlreadyExists, MethodArgumentNotValidException {
		if (patientService.getPatientByFamilyAndGiven(patient.getFamily(), patient.getGiven()).isPresent()) {
			throw new PatientAlreadyExists("A patient already exist with this family and given : " + patient.getFamily()
					+ " " + patient.getGiven());
		}
		return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.CREATED);
	}

	/**
	 * Read - get all patients in list
	 * 
	 * @return list of patients
	 */
	@Operation(summary = "Get all patients")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Patient.class))))
	@GetMapping(value = "/list")
	public ResponseEntity<List<Patient>> getPatients() {
		List<Patient> patients = patientService.getPatients();
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

	/**
	 * Read - get a patient's info by id
	 * 
	 * @param patient id
	 * @return patient
	 */
	@Operation(summary = "Get a patient's infos by id")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Patient.class)))
	@ApiResponse(responseCode = "404", description = "PatientNotFoundException", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorMessage.class)))
	@GetMapping(value = "/{id}")
	public ResponseEntity<Patient> getPatient(
			@Parameter(name = "id", description = "id of the patient to get") @PathVariable("id") Integer id) {
		Patient patient = patientService.getPatient(id)
				.orElseThrow(() -> new PatientNotFoundException("Not found patient with id = " + id));
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	/**
	 * Read - get a patient's info
	 * 
	 * @param patient id
	 * @return patient
	 */
	@Operation(summary = "Get list of patients by family")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Patient.class))))
	@GetMapping(value = "/familyName")
	public ResponseEntity<List<Patient>> getPatientByFamily(
			@Parameter(name = "family", description = "family of the patients to get") @RequestParam("family") String family) {
		return new ResponseEntity<>(patientService.getPatientByFamily(family), HttpStatus.OK);
	}

	/**
	 * Update - update patient's infos
	 * 
	 * @param patient
	 * @return patient
	 */
	@Operation(summary = "Update a patient's info", description = "Update patient's info whith content send in body")
	@ApiResponse(responseCode = "201", description = "Patient updated", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "422", description = "MethodArgumentNotValidException", content = @Content(mediaType = "application/json"))
	@PostMapping(value = "/update")
	public ResponseEntity<Patient> updatePatient(@Valid @RequestBody Patient patient) {
//		if (patientService.getPatientByFamilyAndGiven(patient.getFamily(), patient.getGiven()).isPresent()) {
//			throw new PatientAlreadyExists("A patient already exist with this family and given : " + patient.getFamily()
//					+ " " + patient.getGiven());
//		}
		return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.CREATED);
	}

	/**
	 * Delete patient from DB
	 * 
	 * @param patient id
	 */
	@Operation(summary = "Delete a patient", description = "Delete a patient find by id in DataBase")
	@ApiResponse(responseCode = "204", description = "Successful operation", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "404", description = "PatientNotFoundException", content = @Content(mediaType = "application/json"))
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> deletePatient(
			@Parameter(name = "id", description = "id of the patient to delete") @PathVariable("id") Integer id) {
		patientService.getPatient(id)
				.orElseThrow(() -> new PatientNotFoundException("Not found patient with id = " + id));
		patientService.deletePatient(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
