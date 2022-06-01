package fr.redkissifrott.abernathyClient.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
