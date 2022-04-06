package fr.redkissifrott.abernathyReport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

	private String id;

	private Integer patId;

	private String patName;

	private String recommendations;
}
