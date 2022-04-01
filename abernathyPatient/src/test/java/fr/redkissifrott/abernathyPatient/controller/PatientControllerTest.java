package fr.redkissifrott.abernathyPatient.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fr.redkissifrott.abernathyPatient.model.Patient;
import fr.redkissifrott.abernathyPatient.repository.PatientRepository;
import fr.redkissifrott.abernathyPatient.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {

	@MockBean
	private PatientRepository patientRepository;

	@Autowired
	private PatientService patientService;

	@Autowired
	private MockMvc mockMvc;

	private Optional<Patient> patient = Optional.ofNullable(
			new Patient(UUID.randomUUID(), "family", "given", LocalDate.of(1973, 10, 18), "F", "adress", "phone"));

	private List<Patient> patients = List.of(patient.get());

	@Test
	void addPatientTest() throws Exception {
		mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"Test\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().isOk());
	}

	@Test
	void addPatientAlreadyExistsTest() {
		Mockito.when(patientRepository.findByFamilyAndGiven("Test", "Test")).thenReturn(patient);
		try {
			mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_JSON).content(
					"{\"family\": \"Test\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
					.andExpect(status().is5xxServerError());
		} catch (Exception e) {

		}
	}

//	@Test
//	void getPatients() {
//		Mockito.when(patientRepository.findAll()).thenReturn(patients);
//		mockMvc.perform(get("/patient/list"))
//	}

}
