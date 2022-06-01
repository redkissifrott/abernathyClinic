package fr.redkissifrott.abernathyReport.Test;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import fr.redkissifrott.abernathyReport.model.Patient;
import fr.redkissifrott.abernathyReport.proxies.PatientProxy;

@SpringBootTest
@AutoConfigureMockMvc
class ReportTests {

	@MockBean
	private PatientProxy patientProxy;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getPatientTest() throws Exception {
		Optional<Patient> patient = Optional
				.ofNullable(new Patient(3, "family", "given", LocalDate.of(1973, 10, 18), "F", "adress", "phone"));
		Mockito.when(patientProxy.getPatient(3)).thenReturn(patient);
		mockMvc.perform(get("/3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.family").value("family"));
	}

}
