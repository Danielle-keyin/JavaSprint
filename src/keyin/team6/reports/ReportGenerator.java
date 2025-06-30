package keyin.team6.reports;

import java.time.LocalDate;
import java.util.Scanner;

import keyin.team6.model.Doctor;
import keyin.team6.model.Medication;
import keyin.team6.model.Patient;
import keyin.team6.model.Prescription;
import keyin.team6.system.MedicationTrackingSystem;

public class ReportGenerator {
	
	private MedicationTrackingSystem system;
	
	public ReportGenerator(MedicationTrackingSystem system) {
		this.system = system;
	}
	
	// Prints every doctor, patient, med, and prescription. yeah it's a wall of text...
	public void generateFullReport() {
		System.out.println("\n--- SYSTEM REPORT ---");

		System.out.println("Doctors:");
		for (Doctor doc : this.system.doctorStore.getAllPersons().values()) {
			System.out.println(doc);
		}

		System.out.println("\nPatients:");
		for (Patient p : this.system.patientStore.getAllPersons().values()) {
			System.out.println(p);
		}

		System.out.println("\nMedications:");
		for (Medication m : this.system.getMedications().values()) {
			System.out.println(m);
		}

		System.out.println("\nPrescriptions:");
		for (Prescription pr : this.system.getPrescriptions()) {
			System.out.println(pr);
		}

	}

	// Loops through meds. Prints the expired ones. You’ll act shocked even though no one updates the inventory
	public void checkExpiredMedications() {
		System.out.println("\n--- EXPIRED MEDICATIONS ---");
		boolean found = false;
		for (Medication m : this.system.getMedications().values()) {
			if (m.getExpiryDate().isBefore(LocalDate.now())) {
				System.out.println(m);
				found = true;
			}
		}
		if (!found)
			System.out.println("No expired medications found. Miraculous.");
	}

	// Prints every prescription from a specific doctor. Spoiler: It’s a lot
	public void printPrescriptionsByDoctor(Scanner scanner) {
		System.out.print("Enter doctor name: ");
		String name = scanner.nextLine();

		System.out.println("\n--- Prescriptions by Dr. " + name + " ---");
		for (Prescription p : this.system.getPrescriptions()) {
			if (p.getDoctor().getName().equalsIgnoreCase(name)) {
				System.out.println(p);
			}
		}
	}

	// Lists all drugs prescribed to a patient in the past year
	public void printPatientDrugSummary(Scanner scanner) {
		System.out.print("Enter patient name: ");
		String name = scanner.nextLine();

		for (Patient p : this.system.patientStore.getAllPersons().values()) {
			if (p.getName().equalsIgnoreCase(name)) {
				System.out.println("\n--- Drug Summary for " + p.getName() + " ---");
				for (Prescription pr : p.getPrescriptions()) {
					if (pr.getIssueDate().isAfter(LocalDate.now().minusYears(1))) {
						System.out.println("- " + pr.getMedication().getName());
					}
				}
			}
		}

	}
}
