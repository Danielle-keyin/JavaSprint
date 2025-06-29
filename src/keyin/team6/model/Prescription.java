package keyin.team6.model;

import java.time.LocalDate;

public class Prescription {
    private Doctor doctor;
    private Medication medication;
    private LocalDate issueDate;

    public Prescription(Doctor doctor, Medication medication, LocalDate issueDate) {
        this.doctor = doctor;
        this.medication = medication;
        this.issueDate = issueDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Medication getMedication() {
        return medication;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "doctor=" + doctor.getName() +
                ", medication=" + medication +
                ", issueDate=" + issueDate +
                '}';
    }
}