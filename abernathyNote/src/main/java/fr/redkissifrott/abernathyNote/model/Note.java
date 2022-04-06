package fr.redkissifrott.abernathyNote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Note")
@Data
public class Note {

	@Id
	private String id;

	private Integer patId;

	private String patName;

	private String recommendations;

	public Note(Integer patId, String patName, String recommendations) {
		super();
		this.patId = patId;
		this.patName = patName;
		this.recommendations = recommendations;
	}

	@Override
	public String toString() {
		return "patId=" + patId + "¬e=Patient: " + patName + " Practitioner's notes/recommendations: "
				+ recommendations;
	}

}
