package fr.redkissifrott.abernathyNote.model;

import java.beans.JavaBean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "Note")
@JavaBean
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {

	@Id
	private String id;

	@NotNull(message = "Patient's id can't be null")
	private Integer patId;

	private String patName;

	@NotBlank(message = "Recommendations can't be blank")
	private String recommendations;

	public Note(@NotNull(message = "Patient's id can't be null") Integer patId, String patName,
			@NotBlank(message = "Recommendations can't be blank") String recommendations) {
		super();
		this.patId = patId;
		this.patName = patName;
		this.recommendations = recommendations;
	}

}
