package fr.redkissifrott.abernathyReport.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Patient {
	private int id;
	private String family;
	private String given;
	private LocalDate dob;
	private String sex;
	private String address;
	private String phone;
}
