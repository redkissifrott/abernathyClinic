package fr.redkissifrott.abernathyReport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.redkissifrott.abernathyReport.model.Patient;
import fr.redkissifrott.abernathyReport.model.Report;
import fr.redkissifrott.abernathyReport.proxies.PatientProxy;
import fr.redkissifrott.abernathyReport.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	PatientProxy patientProxy;

	@Autowired
	ReportService reportService;

	@GetMapping("/{patId}")
	public Report getReport(@PathVariable("patId") Integer patId) {
		Patient patient = patientProxy.getPatient(patId).get();
		Report report = new Report(patId, patient.getFamily(), patient.getGiven(), patient.getSex(),
				reportService.ageFromBirthdate(patient.getDob()), reportService.getAssessment(patId));
		return report;
	}

}
