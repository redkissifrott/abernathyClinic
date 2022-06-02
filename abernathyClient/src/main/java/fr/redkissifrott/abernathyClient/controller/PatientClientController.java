package fr.redkissifrott.abernathyClient.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.redkissifrott.abernathyClient.model.Note;
import fr.redkissifrott.abernathyClient.model.Patient;
import fr.redkissifrott.abernathyClient.proxies.NoteProxy;
import fr.redkissifrott.abernathyClient.proxies.PatientProxy;

@Controller
@RequestMapping("/patient")
public class PatientClientController {
	private Logger logger;

	@Autowired
	PatientProxy patientProxy;

	@Autowired
	NoteProxy noteProxy;

	@GetMapping("/list")
	public String patientsList(Model model) {
		model.addAttribute("patientsList", patientProxy.getPatients());
		return "patient/list";
	}

	@GetMapping("/infos")
	public String getPatient(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("patientInfos", patientProxy.getPatient(id).get());
		model.addAttribute("patientNotes", noteProxy.getNotes(id));
		return "patient/infos";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "patient/add";
	}

	@PostMapping("/addPatient")
	public String addPatient(@ModelAttribute Patient patient, Model model) {
		patientProxy.addPatient(patient);
		model.addAttribute("patientsList", patientProxy.getPatients());
		return "patient/list";
	}

	@GetMapping("/delete")
	public String deletePatient(@RequestParam("id") Integer id, Model model) {
		patientProxy.deletePatient(id);
		model.addAttribute("patientsList", patientProxy.getPatients());
		return "patient/list";
	}

	@GetMapping("/update")
	public String updatePatient(@RequestParam("id") Integer id, @ModelAttribute Patient patient, Model model) {
		model.addAttribute("patientInfos", patientProxy.getPatient(id).get());
		return "patient/update";
	}

	@PostMapping("/addNote")
	public String addNote(@RequestParam("id") Integer id, @RequestParam("recommendation") String recommendation,
			Model model) {
		Patient patient = patientProxy.getPatient(id).get();
		Note note = new Note(id, patient.getFamily(), recommendation);
		noteProxy.addNote(note);
		model.addAttribute("patientInfos", patientProxy.getPatient(id).get());
		model.addAttribute("patientNotes", noteProxy.getNotes(id));
		return "patient/infos";
	}

	@GetMapping("/update-note")
	public String updateNote(@RequestParam("noteId") String noteId, @ModelAttribute Note note, Model model) {
//		logger.debug(noteId);
		model.addAttribute("note", noteProxy.getNote(noteId).get());
		return "patient/update-note";
	}

	@PostMapping("/updateNoteAction")
	public String updateNoteAction(@ModelAttribute Note note, Model model) {
		Note noteSaved = noteProxy.addNote(note);
		model.addAttribute("patientInfos", patientProxy.getPatient(noteSaved.getPatId()).get());
		model.addAttribute("patientNotes", noteProxy.getNotes(noteSaved.getPatId()));
		return "patient/infos";
	}

	@GetMapping("/deleteNote")
	public String deleteNote(@RequestParam("noteId") String noteId, Model model) {
		Note noteDeleted = noteProxy.getNote(noteId).get();
		Integer patId = noteDeleted.getPatId();
		noteProxy.deleteNote(noteId);
		model.addAttribute("patientInfos", patientProxy.getPatient(patId).get());
		model.addAttribute("patientNotes", noteProxy.getNotes(patId));
		return "patient/infos";
	}

}
