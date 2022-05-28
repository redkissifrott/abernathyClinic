package fr.redkissifrott.abernathyReport.service;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import fr.redkissifrott.abernathyReport.model.Note;
import fr.redkissifrott.abernathyReport.proxies.NoteProxy;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReportServiceTest {

	@MockBean
	private NoteProxy noteProxy;

	@Autowired
	private ReportService reportService;

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

}
