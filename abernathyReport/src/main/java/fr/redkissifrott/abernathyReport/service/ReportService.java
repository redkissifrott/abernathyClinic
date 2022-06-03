package fr.redkissifrott.abernathyReport.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.redkissifrott.abernathyReport.constant.Assessment;
import fr.redkissifrott.abernathyReport.constant.Term;
import fr.redkissifrott.abernathyReport.model.Note;
import fr.redkissifrott.abernathyReport.proxies.NoteProxy;
import fr.redkissifrott.abernathyReport.proxies.PatientProxy;

@Service
public class ReportService {

	@Autowired
	PatientProxy patientProxy;

	@Autowired
	NoteProxy noteProxy;

	/**
	 * 
	 * @param patId
	 * @return assessment - the risk of diabete
	 */
	public Assessment getAssessment(Integer patId) {
		int terms = termsCount(patId);
		String sex = patientProxy.getPatient(patId).get().getSex();
		int age = ageFromBirthdate(patientProxy.getPatient(patId).get().getDob());

		Assessment assessment = Assessment.NONE;

		if (age < 30) {
			if (sex == "F") {
				if (terms > 3) {
					assessment = Assessment.IN_DANGER;
				}
				if (terms > 6) {
					assessment = Assessment.EARLY_ONSET;
				}
			} else {
				if (terms > 2) {
					assessment = Assessment.IN_DANGER;
				}
				if (terms > 4) {
					assessment = Assessment.EARLY_ONSET;
				}
			}
		} else {
			if (terms > 1) {
				assessment = Assessment.BORDERLINE;
			}
			if (terms > 5) {
				assessment = Assessment.IN_DANGER;
			}
			if (terms > 7) {
				assessment = Assessment.EARLY_ONSET;
			}
		}

		return assessment;
	}

	/**
	 * Count of risk's terms in patient's notes
	 * 
	 * @param patId - the patient's ID
	 * @return the number of occurrences of risk's terms
	 */
	public int termsCount(Integer patId) {
		ArrayList<Note> notes = noteProxy.getNotes(patId);
		EnumSet<Term> terms = EnumSet.allOf(Term.class);
		int termCount = 0;
		for (Note note : notes) {
			for (Term term : terms) {
				if (note.getRecommendations().toUpperCase().contains(term.toString())) {
					termCount++;
				}
			}
		}
		return termCount;

	}

	/**
	 * 
	 * @param birthdate
	 * @return age
	 */
	public Integer ageFromBirthdate(LocalDate birthdate) {
		Integer age = Period.between(birthdate, LocalDate.now()).getYears();
		return age;
	}

}
