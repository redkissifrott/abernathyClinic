package fr.redkissifrott.abernathyClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.redkissifrott.abernathyClient.model.Note;
import fr.redkissifrott.abernathyClient.proxies.NoteProxy;

@Controller
public class NoteClientController {

	@Autowired
	NoteProxy noteProxy;

	@GetMapping("/notes/{patId}")
	public Iterable<Note> getNotes(@PathVariable("patId") Integer patId, Model model) {
		model.addAttribute("patientNotes", noteProxy.getNotes(patId));
		return null;
	}

}
