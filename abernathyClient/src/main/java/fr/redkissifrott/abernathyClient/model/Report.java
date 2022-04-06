package fr.redkissifrott.abernathyClient.model;

import fr.redkissifrott.abernathyClient.constant.Assessment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Report {
	private int patId;
	private String family;
	private String given;
	private String sex;
	private Integer age;
	private Assessment assessment;
}
