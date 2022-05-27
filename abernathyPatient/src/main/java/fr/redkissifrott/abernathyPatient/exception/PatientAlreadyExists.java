package fr.redkissifrott.abernathyPatient.exception;

public class PatientAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PatientAlreadyExists(String msg) {
		super(msg);
	}

}
