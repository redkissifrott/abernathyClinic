package fr.redkissifrott.abernathyPatient.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.redkissifrott.abernathyPatient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Optional<Patient> findByFamilyAndGiven(String family, String given);

}
