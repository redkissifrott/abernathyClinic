package fr.redkissifrott.abernathyClient.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Note {

	public Note(Integer patId, String patName,
			@NotBlank(message = "The note must not be empty") String recommendations) {
		super();
		this.patId = patId;
		this.patName = patName;
		this.recommendations = recommendations;
	}

	private String id;

	private Integer patId;

	private String patName;

	@NotBlank(message = "The note must not be empty")
	private String recommendations;
}
