package fr.redkissifrott.abernathyPatient.service;

import java.util.List;
import java.util.Optional;

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

	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	public Optional<Patient> getPatient(Integer id) {
		return patientRepository.findById(id);
	}

	public Optional<Patient> getPatientByFamilyAndGiven(String family, String given) {
		return patientRepository.findByFamilyAndGiven(family, given);
	}

	public List<Patient> getPatientByFamily(String family) {
		return patientRepository.findByFamily(family);
	}

	@Transactional
	public void deletePatient(Integer id) {
		patientRepository.deleteById(id);

	}

}
