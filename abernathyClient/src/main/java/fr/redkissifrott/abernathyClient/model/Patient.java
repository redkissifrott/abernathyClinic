package fr.redkissifrott.abernathyClient.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Patient {
	private UUID id;
	@NotBlank(message = "Family is mandatory")
	private String family;
	@NotBlank(message = "Given is mandatory")
	private String given;
	@NotNull(message = "DOB is mandatory")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	@NotBlank(message = "Sex is mandatory")
	private String sex;
	@NotBlank(message = "Adress is mandatory")
	private String address;
	@NotBlank(message = "Phone is mandatory")
	private String phone;
}
