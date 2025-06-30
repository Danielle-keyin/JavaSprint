package keyin.team6.model;

import java.time.LocalDate;

public class Prescription {
	private Patient patient;
	private Doctor doctor;
	private Medication medication;
	private LocalDate issueDate;

	public Prescription(Patient patient, Doctor doctor, Medication medication, LocalDate issueDate) {
		this.patient = patient;
		this.doctor = doctor;
		this.medication = medication;
		this.issueDate = issueDate;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public Medication getMedication() {
		return this.medication;
	}

	public LocalDate getIssueDate() {
		return this.issueDate;
	}
	
	// Prescriptions are immutable, so no setters are provided

	@Override
	public String toString() {
		return "Prescription for '" + this.patient.getName() + "' by Dr. " + this.doctor.getName() +
				" for medication '" + this.medication.getName() + "' issued on " + this.issueDate;
	}
}