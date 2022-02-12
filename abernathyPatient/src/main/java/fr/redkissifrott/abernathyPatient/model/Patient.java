package fr.redkissifrott.abernathyPatient.model;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotBlank(message = "Family is mandatory")
	@Column(name = "family")
	private String family;

	@NotBlank(message = "Given is mandatory")
	@Column(name = "given")
	private String given;

	@NotNull(message = "DOB is mandatory")
	@Column(name = "dob")
	private LocalDate dob;

	@NotBlank(message = "Sex is mandatory")
	@Column(name = "sex")
	private String sex;

	@NotBlank(message = "Adress is mandatory")
	@Column(name = "address")
	private String address;

	@NotBlank(message = "Phone is mandatory")
	@Column(name = "phone")
	private String phone;

}
