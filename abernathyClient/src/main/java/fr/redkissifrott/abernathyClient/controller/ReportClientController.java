package fr.redkissifrott.abernathyClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.redkissifrott.abernathyClient.proxies.ReportProxy;

@Controller
@RequestMapping("/patient")
public class ReportClientController {

	@Autowired
	ReportProxy reportProxy;

	@GetMapping("/assess")
	public String getReport(@RequestParam("id") Integer patId, Model model) {
		model.addAttribute("report", reportProxy.getReport(patId));
		return "patient/assess";
	}

}
