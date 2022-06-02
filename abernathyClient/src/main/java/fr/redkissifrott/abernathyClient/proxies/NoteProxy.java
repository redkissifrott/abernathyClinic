package fr.redkissifrott.abernathyClient.proxies;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.redkissifrott.abernathyClient.model.Note;

@FeignClient(name = "abernathyNote", url = "${noteProxy}")
public interface NoteProxy {

	@PostMapping("/patHistory/add")
	public Note addNote(@RequestBody Note note);

	@GetMapping("/patHistory/{id}")
	public Optional<Note> getNote(@PathVariable("id") String id);

	@GetMapping("/patHistory/notes/{patId}")
	public Iterable<Note> getNotes(@PathVariable("patId") Integer patId);

	@DeleteMapping("/patHistory/delete/{id}")
	public ResponseEntity<Object> deleteNote(@PathVariable("id") String id);
}
