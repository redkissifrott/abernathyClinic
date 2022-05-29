package fr.redkissifrott.abernathyNote.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.redkissifrott.abernathyNote.exception.CustomErrorMessage;
import fr.redkissifrott.abernathyNote.model.Note;
import fr.redkissifrott.abernathyNote.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "NoteController", description = "Patient Notes CRUD")
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
	@Operation(summary = "Add a note", description = "Add note send in body to DataBase")
	@ApiResponse(responseCode = "201", description = "Note created", content = @Content(mediaType = "application/json"))
	@ApiResponse(responseCode = "422", description = "MethodArgumentNotValidException", content = @Content(mediaType = "application/json"))

	@PostMapping("/add")
	public ResponseEntity<Note> addNote(@Valid @RequestBody Note note) throws MethodArgumentNotValidException {
		return new ResponseEntity<Note>(noteService.saveNote(note), HttpStatus.CREATED);
	}

	/**
	 * Read - get a note by its id
	 * 
	 * @param id
	 * @return note
	 */
	@Operation(summary = "Get a note by its id")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Note.class)))
	@ApiResponse(responseCode = "404", description = "NoteNotFoundException", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomErrorMessage.class)))
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
	@Operation(summary = "Get list of all patient's note by its id")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Note.class))))
	@GetMapping("/notes/{patId}")
	public ResponseEntity<List<Note>> getNotes(@Parameter(name = "patient's id") @PathVariable("patId") Integer patId) {
		return new ResponseEntity<>(noteService.getNotes(patId), HttpStatus.OK);
	}

}
