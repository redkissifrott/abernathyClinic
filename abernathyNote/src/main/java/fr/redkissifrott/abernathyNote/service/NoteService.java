package fr.redkissifrott.abernathyNote.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.redkissifrott.abernathyNote.model.Note;
import fr.redkissifrott.abernathyNote.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;

	public Note saveNote(Note note) {
		return noteRepository.save(note);
	}

	public Optional<Note> getNote(String id) {
		return noteRepository.findById(id);
	}

	public List<Note> getNotes(Integer patId) {
		return noteRepository.findAllByPatId(patId);
	}

}
