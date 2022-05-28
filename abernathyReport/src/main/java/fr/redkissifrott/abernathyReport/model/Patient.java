package fr.redkissifrott.abernathyReport.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	private int id;
	private String family;

	public Patient(int id, LocalDate dob, String sex) {
		super();
		this.id = id;
		this.dob = dob;
		this.sex = sex;
	}

	private String given;
	private LocalDate dob;
	private String sex;
	private String address;
	private String phone;
}
