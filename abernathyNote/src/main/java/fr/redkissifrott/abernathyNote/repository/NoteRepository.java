package fr.redkissifrott.abernathyNote.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.redkissifrott.abernathyNote.model.Note;

public interface NoteRepository extends MongoRepository<Note, String> {

	Iterable<Note> findAllByPatId(Integer patId);

}
