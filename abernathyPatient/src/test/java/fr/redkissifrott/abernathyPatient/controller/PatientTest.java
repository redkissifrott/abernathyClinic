package fr.redkissifrott.abernathyPatient.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fr.redkissifrott.abernathyPatient.exception.PatientNotFoundException;
import fr.redkissifrott.abernathyPatient.model.Patient;
import fr.redkissifrott.abernathyPatient.repository.PatientRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PatientTest {

	@MockBean
	private PatientRepository patientRepository;

	@Autowired
	private MockMvc mockMvc;

	private Optional<Patient> patient = Optional
			.ofNullable(new Patient(3, "family", "given", LocalDate.of(1973, 10, 18), "F", "adress", "phone"));
	private Optional<Patient> patient2 = Optional
			.ofNullable(new Patient(4, "family2", "given2", LocalDate.of(1973, 10, 18), "F", "adress", "phone"));

	private List<Patient> patients = List.of(patient.get(), patient2.get());

	@Test
	void addPatientTest() throws Exception {
		mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"Test\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	void addPatientAlreadyExistsTest() throws Exception {
		Mockito.when(patientRepository.findByFamilyAndGiven("Test", "Test")).thenReturn(patient);
		mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"Test\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void addPatientInvalidParameterTest() throws Exception {
		mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void addPatientInvalidSexParameterTest() throws Exception {
		mockMvc.perform(post("/patient/add").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"G\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void updatePatientTest() throws Exception {
		Mockito.when(patientRepository.save(patient.get())).thenReturn(patient.get());
		mockMvc.perform(put("/patient/").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"Test\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	void updatePatientAlreadyExistsTest() throws Exception {
		Mockito.when(patientRepository.findByFamilyAndGiven("Test", "Test")).thenReturn(patient);
		mockMvc.perform(put("/patient/").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"Test\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void getPatientTest() throws Exception {
		Mockito.when(patientRepository.findById(3)).thenReturn(patient);
		mockMvc.perform(get("/patient/3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.family").value("family"));
	}

	@Test
	void getPatientNotFoundTest() throws Exception {
		Mockito.when(patientRepository.save(patient.get())).thenThrow(new PatientNotFoundException(null));
		mockMvc.perform(get("/patient/3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	void getPatientsTest() throws Exception {
		when(patientRepository.findAll()).thenReturn(patients);
		mockMvc.perform(get("/patient/list").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$..*").isNotEmpty())
				.andExpect(jsonPath("$.[0].given").value("given")).andExpect(jsonPath("$.[1].given").value("given2"));
	}

	@Test
	void getPatientByFamilyTest() throws Exception {
		when(patientRepository.findByFamily("family")).thenReturn(patients);
		mockMvc.perform(get("/patient/familyName?family=family").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..*").isNotEmpty()).andExpect(jsonPath("$.[0].given").value("given"));
	}

	@Test
	void deletePatientTest() throws Exception {
		Mockito.when(patientRepository.findById(3)).thenReturn(patient);
		mockMvc.perform(delete("/patient/delete/3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}

	@Test
	void deletePatientNotFoundTest() throws Exception {
		Mockito.when(patientRepository.save(patient.get())).thenThrow(new PatientNotFoundException(null));
		mockMvc.perform(delete("/patient/delete/3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
