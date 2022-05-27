package fr.redkissifrott.abernathyNote.model;

import java.beans.JavaBean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Note")
@JavaBean
@Data
public class Note {

	@Id
	private String id;

	@NotNull(message = "Patient's id can't be null")
	private Integer patId;

	private String patName;

	@NotBlank(message = "Recommendations can't be blank")
	private String recommendations;

	@Override
	public String toString() {
		return "patId=" + patId + "¬e=Patient: " + patName + " Practitioner's notes/recommendations: "
				+ recommendations;
	}

	public Note(@NotNull(message = "Patient's id can't be null") Integer patId, String patName,
			@NotBlank(message = "Recommendations can't be blank") String recommendations) {
		super();
		this.patId = patId;
		this.patName = patName;
		this.recommendations = recommendations;
	}

}
