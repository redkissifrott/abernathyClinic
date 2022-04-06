package fr.redkissifrott.abernathyReport.proxies;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.redkissifrott.abernathyReport.model.Note;

@FeignClient(name = "abernathyNote", url = "${noteProxy}")
public interface NoteProxy {

	@GetMapping("/patHistory/notes/{patId}")
	public ArrayList<Note> getNotes(@PathVariable("patId") Integer patId);

}
