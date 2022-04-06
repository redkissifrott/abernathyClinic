package fr.redkissifrott.abernathyNote.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.redkissifrott.abernathyNote.model.Note;
import fr.redkissifrott.abernathyNote.service.NoteService;

@RestController
@RequestMapping("/patHistory")
public class NoteController {
	@Autowired
	NoteService noteService;

	/**
	 * Create - add a note for a patient in DB
	 * 
	 * @param note
	 * @return note
	 */
	@PostMapping("/add")
	public Note addNote(@RequestBody Note note) {
		noteService.saveNote(note);
		return note;
	}

	/**
	 * Read - get a note by its id
	 * 
	 * @param id
	 * @return note
	 */
	@GetMapping("/{id}")
	public Optional<Note> getNote(@PathVariable("id") String id) {
		return noteService.getNote(id);
	}

	/**
	 * Read - get all patient's notes by patient id
	 * 
	 * @param patId
	 * @return list of notes
	 */
	@GetMapping("/notes/{patId}")
	public Iterable<Note> getNotes(@PathVariable("patId") Integer patId) {
		return noteService.getNotes(patId);
	}

}
