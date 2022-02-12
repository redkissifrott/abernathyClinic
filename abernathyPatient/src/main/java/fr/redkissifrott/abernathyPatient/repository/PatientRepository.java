package fr.redkissifrott.abernathyPatient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.redkissifrott.abernathyPatient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
