package fr.redkissifrott.abernathyNote.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.redkissifrott.abernathyNote.model.Note;

public interface NoteRepository extends MongoRepository<Note, String> {

	List<Note> findAllByPatId(Integer patId);

}
