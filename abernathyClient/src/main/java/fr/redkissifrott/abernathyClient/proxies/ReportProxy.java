package fr.redkissifrott.abernathyClient.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.redkissifrott.abernathyClient.model.Report;

@FeignClient(name = "abernathyReport", url = "${reportProxy}")
public interface ReportProxy {

	@GetMapping("/{patId}")
	public Report getReport(@PathVariable("patId") Integer patId);
}
