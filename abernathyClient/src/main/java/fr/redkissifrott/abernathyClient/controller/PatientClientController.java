package fr.redkissifrott.abernathyClient.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.redkissifrott.abernathyClient.model.Patient;
import fr.redkissifrott.abernathyClient.proxies.PatientProxy;

@Controller
@RequestMapping("/patient")
public class PatientClientController {
	private Logger logger;

	@Autowired
	PatientProxy patientProxy;

	@GetMapping("/list")
	public String patientsList(Model model) {
		// logger.debug("patients" + patientProxy.getPatients().size());
		model.addAttribute("patientsList", patientProxy.getPatients());
		return "patient/list";
	}

	@GetMapping("/infos")
	public String getPatient(@RequestParam("id") UUID id, Model model) {
		model.addAttribute("patientInfos", patientProxy.getPatient(id).get());
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
	public String deletePatient(@RequestParam("id") UUID id, Model model) {
		patientProxy.deletePatient(id);
		model.addAttribute("patientsList", patientProxy.getPatients());
		return "patient/list";
	}

}
