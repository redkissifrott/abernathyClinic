package fr.redkissifrott.abernathyClient.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fr.redkissifrott.abernathyClient.constant.Assessment;
import fr.redkissifrott.abernathyClient.model.Note;
import fr.redkissifrott.abernathyClient.model.Patient;
import fr.redkissifrott.abernathyClient.model.Report;
import fr.redkissifrott.abernathyClient.proxies.NoteProxy;
import fr.redkissifrott.abernathyClient.proxies.PatientProxy;
import fr.redkissifrott.abernathyClient.proxies.ReportProxy;

@SpringBootTest
@AutoConfigureMockMvc
class AbernathyClientTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientProxy patientProxy;

	@MockBean
	private NoteProxy noteProxy;

	@MockBean
	private ReportProxy reportProxy;

	private Optional<Patient> patient = Optional
			.ofNullable(new Patient(3, "family", "given", LocalDate.of(1973, 10, 18), "F", "adress", "phone"));
	private Optional<Patient> patient2 = Optional
			.ofNullable(new Patient(4, "family2", "given2", LocalDate.of(1973, 10, 18), "F", "adress", "phone"));

	private List<Patient> patients = List.of(patient.get(), patient2.get());

	private Optional<Note> note = Optional.ofNullable(new Note("1", 3, "patName", "recommendation text"));
	private Optional<Note> note2 = Optional.ofNullable(new Note("2", 3, "patName", "recommendation text"));
	private List<Note> notes = List.of(note.get(), note2.get());

	private Report report = new Report(3, "family", "given", "F", 31, Assessment.EARLY_ONSET);

	@Test
	void getPatientsTest() throws Exception {
		when(patientProxy.getPatients()).thenReturn(patients);
		mockMvc.perform(get("/patient/list").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(view().name("patient/list"))
				.andExpect(content().string(containsString("family")));
	}

	@Test
	void getPatientTest() throws Exception {
		when(patientProxy.getPatient(3)).thenReturn(patient);
		when(noteProxy.getNotes(3)).thenReturn(notes);
		mockMvc.perform(get("/patient/infos?id=3").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(view().name("patient/infos"))
				.andExpect(content().string(containsString("family")));
	}

	@Test
	void addFormTest() throws Exception {
		mockMvc.perform(get("/patient/add").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(view().name("patient/add"));
	}

	@Test
	void addPatientTest() throws Exception {
		when(patientProxy.addPatient(patient.get())).thenReturn(patient.get());
		mockMvc.perform(post("/patient/addPatient").contentType(MediaType.APPLICATION_JSON).content(
				"{\"family\": \"Test\", \"given\": \"Test\", \"dob\": \"1966-12-31\", \"sex\": \"F\", \"address\": \"1 Brookside St\", \"phone\": \"100-222-3333\"}"))
				.andExpect(status().isOk()).andExpect(view().name("patient/list"));
	}

	@Test
	void updateTest() throws Exception {
		when(patientProxy.getPatient(3)).thenReturn(patient);
		mockMvc.perform(get("/patient/update?id=3").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(view().name("patient/update"))
				.andExpect(content().string(containsString("family")));
	}

	@Test
	void addNoteTest() throws Exception {
		when(patientProxy.getPatient(3)).thenReturn(patient);
		when(noteProxy.addNote(note.get())).thenReturn(note.get());
		mockMvc.perform(post("/patient/addNote?id=3&recommendation=praticien note")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": \"3\", \"patName\": \"family\", \"recommendations\": \"Recommendation text\"}"))
				.andExpect(status().isOk()).andExpect(view().name("patient/infos"));
	}

	@Test
	void getReportTest() throws Exception {
		when(reportProxy.getReport(3)).thenReturn(report);
		mockMvc.perform(get("/patient/assess?id=3").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(view().name("patient/assess"))
				.andExpect(content().string(containsString("family")));
	}
}
