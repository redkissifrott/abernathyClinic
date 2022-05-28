package fr.redkissifrott.abernathyReport.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.redkissifrott.abernathyReport.constant.Assessment;
import fr.redkissifrott.abernathyReport.model.Note;
import fr.redkissifrott.abernathyReport.model.Patient;
import fr.redkissifrott.abernathyReport.proxies.NoteProxy;
import fr.redkissifrott.abernathyReport.proxies.PatientProxy;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReportServiceTest {

	private Logger logger;

	@MockBean
	NoteProxy noteProxy;

	@MockBean
	PatientProxy patientProxy;

	@MockBean
	ReportService reportServiceMock;

	@Autowired
	ReportService reportService;

	@Test
	void termsCountTest() {
		ArrayList<Note> notes = new ArrayList<Note>();
		Note note1 = new Note("1", 7, "patName", "bla smoker");
		Note note2 = new Note("2", 7, "patName", "bla smoker relapse");
		notes.add(note1);
		notes.add(note2);

		Mockito.when(noteProxy.getNotes(7)).thenReturn(notes);

		int termsCount = reportService.termsCount(7);
		assertEquals(3, termsCount);
	}

	@Test
	void getAssesmentFLess30y() {
		Patient patient = new Patient(3, LocalDate.now().minusYears(25), "F");
		Mockito.when(reportService.termsCount(3)).thenReturn(1);
		Mockito.when(patientProxy.getPatient(3)).thenReturn(patient);

		Assessment assessment = reportService.getAssessment(3);

		assertEquals(Assessment.IN_DANGER, assessment);
	}

}
