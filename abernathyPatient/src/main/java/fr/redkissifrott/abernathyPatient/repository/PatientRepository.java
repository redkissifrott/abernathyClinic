package fr.redkissifrott.abernathyPatient.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.redkissifrott.abernathyPatient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Optional<Patient> findById(UUID id);

	void deleteById(UUID id);

}
