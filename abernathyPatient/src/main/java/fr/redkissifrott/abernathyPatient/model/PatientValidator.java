package fr.redkissifrott.abernathyPatient.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PatientValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Patient.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Patient patient = (Patient) target;
		if (patient.getSex() == null || !patient.getSex().equals("M") && !patient.getSex().equals("F")) {
			errors.rejectValue("sex", "sex.wrongValue", "sex field must be either M or F");
		}
	}

}
