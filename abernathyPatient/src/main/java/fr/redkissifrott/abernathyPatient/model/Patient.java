package fr.redkissifrott.abernathyPatient.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

	@Schema(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

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

	@Schema(name = "address", description = "Optional")
	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

}
