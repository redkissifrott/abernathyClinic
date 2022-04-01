package fr.redkissifrott.abernathyPatient.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.redkissifrott.abernathyPatient.model.Patient;
import fr.redkissifrott.abernathyPatient.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}

	public Iterable<Patient> getPatients() {
		return patientRepository.findAll();
	}

	public Optional<Patient> getPatient(UUID id) {
		return patientRepository.findById(id);
	}

	public Optional<Patient> getPatientByFamilyAndGiven(String family, String given) {
		return patientRepository.findByFamilyAndGiven(family, given);
	}

	@Transactional
	public void deletePatient(UUID id) {
		patientRepository.deleteById(id);

	}

}
