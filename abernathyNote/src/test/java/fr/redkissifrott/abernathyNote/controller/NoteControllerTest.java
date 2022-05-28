package fr.redkissifrott.abernathyNote.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import fr.redkissifrott.abernathyNote.model.Note;
import fr.redkissifrott.abernathyNote.repository.NoteRepository;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

	@MockBean
	private NoteRepository noteRepository;

	@Autowired
	private MockMvc mockMvc;

	private Optional<Note> note = Optional.ofNullable(new Note("1", 3, "patName", "recommendation text"));
	private Optional<Note> note2 = Optional.ofNullable(new Note("2", 3, "patName2", "recommendation text"));
	private List<Note> notes = List.of(note.get(), note2.get());

	@Test
	void addNoteTest() throws Exception {
		mockMvc.perform(post("/patHistory/add").contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": \"1\", \"patName\": \"Pat\", \"recommendations\": \"Recommendation text\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	void addNoteInvalidParameterTest() throws Exception {
		mockMvc.perform(post("/patHistory/add").contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": \"\", \"patName\": \"Pat\", \"recommendations\": \"Recommendation text\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	void getNoteTest() throws Exception {
		Mockito.when(noteRepository.findById("1")).thenReturn(note);
		mockMvc.perform(get("/patHistory/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.patName").value("patName"));
	}

	@Test
	void getNotesTest() throws Exception {
		Mockito.when(noteRepository.findAllByPatId(3)).thenReturn(notes);
		mockMvc.perform(get("/patHistory/notes/3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[0].patName").value("patName"));
	}

}
