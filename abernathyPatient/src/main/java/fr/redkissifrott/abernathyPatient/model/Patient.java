package fr.redkissifrott.abernathyPatient.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	public Patient(UUID id, @NotBlank(message = "Family is mandatory") String family,
			@NotBlank(message = "Given is mandatory") String given,
			@Past(message = "Birthdate can't be future") @NotNull(message = "Birthdate is mandatory") LocalDate dob,
			@NotNull(message = "Sex is mandatory") String sex, String address, String phone) {
		super();
		this.id = id;
		this.family = family;
		this.given = given;
		this.dob = dob;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
	}

	@NotBlank(message = "Family is mandatory")
	@Column(name = "family")
	private String family;

	@NotBlank(message = "Given is mandatory")
	@Column(name = "given")
	private String given;

	@Past(message = "Birthdate can't be future")
	@NotNull(message = "Birthdate is mandatory")
	@Column(name = "dob")
	private LocalDate dob;

	@NotNull(message = "Sex is mandatory")
	@Column(name = "sex")
	private String sex;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	public Patient() {
		super();
	}

}
