package keyin.team6.system;

import keyin.team6.model.Doctor; //Here is Artem u've got path keyin.team6.keyin.team6 (twice) so I changed it
import keyin.team6.model.Patient;
import keyin.team6.model.Person;
import keyin.team6.model.Medication;
import keyin.team6.model.Prescription;
import keyin.team6.utils.Utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MedicationTrackingSystem {

	private PersonStore<Patient> patientStore;
	private PersonStore<Doctor> doctorStore;
	private Map<String, Medication> medications;
	private List<Prescription> prescriptions;

	public MedicationTrackingSystem() {
		this.patientStore = new PersonStore<>();
		this.doctorStore = new PersonStore<>();
		this.medications = new HashMap<>();
		this.prescriptions = new ArrayList<>();
	}

	// Adds a new patient. Yes, manually. No, there's no form. Welcome to 2003
	// bahahaha
	public void addPatient(Scanner scanner) {

		System.out.print("Enter Patient Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Patient Age: ");
		int age = -1;
		while (true) {
			try {
				age = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a valid age: ");
				continue; // Prompt again
			}
		}

		System.out.print("Enter Patient Phone Number: ");
		String phoneNumber = scanner.nextLine();

		int newId = this.patientStore.getPersonCount() + 1; // Simple ID generation
		Patient newPatient = new Patient(String.valueOf(newId), name, age, phoneNumber);

		this.patientStore.addPerson(newPatient);
	}

	// Deletes a patient from the system
	public void deletePatient(Scanner scanner) {
		System.out.print("Enter Patient ID to delete: ");
		String id = scanner.nextLine();

		if (!this.patientStore.hasPerson(id)) {
			System.out.println("Patient with ID " + id + " not found.");
			return;
		}

		var patient = this.patientStore.getPerson(id);
		System.out.print("Delete '" + patient.getName() + "'? (yes/no): ");
		String confirmation = scanner.nextLine();
		if (confirmation.equalsIgnoreCase("yes")) {
			this.patientStore.deletePerson(id);
			System.out.println("Patient with ID " + id + " has been deleted.");
		} else {
			System.out.println("Deletion cancelled.");
		}
	}

	// Because someone *definitely* entered "Jhn Smth" by accident. Time to fix it
	public void editPatient(Scanner scanner) {
		System.out.print("Enter Patient ID to edit: ");
		String id = scanner.nextLine();

		if (!this.patientStore.hasPerson(id)) {
			System.out.println("Patient with ID " + id + " not found.");
			return;
		}

		var patient = this.patientStore.getPerson(id);
		System.out.println("Editing Patient: " + patient.getName());
		System.out.println("1. ID (current: " + patient.getId() + ")");
		System.out.println("2. NAME (current: " + patient.getName() + ")");
		System.out.println("3. AGE (current: " + patient.getAge() + ")");
		System.out.println("4. PHONE NUMBER (current: " + patient.getPhoneNumber() + ")");
		System.out.print("Choose field to edit (1-4) or 0 to exit: ");

		while (true) {
			String choice = scanner.nextLine();

			if (Utilities.isQuitChoice(choice)) {
				System.out.println("Exiting edit mode.");
				return;
			}

			switch (choice) {
			case "1":
				System.out.print("Enter new ID: ");
				String newId = scanner.nextLine();
				this.patientStore.changePersonId(patient.getId(), newId);
				System.out.println("Patient ID updated to " + newId);
				return;
			case "2":
				System.out.print("Enter new Name: ");
				String newName = scanner.nextLine();
				patient.setName(newName);
				System.out.println("Patient name updated to " + newName);
				return;
			case "3":
				System.out.print("Enter new Age: ");
				int newAge = Integer.parseInt(scanner.nextLine());
				patient.setAge(newAge);
				System.out.println("Patient age updated to " + newAge);
				return;
			case "4":
				System.out.print("Enter new Phone Number: ");
				String newPhoneNumber = scanner.nextLine();
				patient.setPhoneNumber(newPhoneNumber);
				System.out.println("Patient phone number updated to " + newPhoneNumber);
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	// Search for a patient. If you get the name even slightly wrong, oh well
	public void searchPatient(Scanner scanner) {
		System.out.println("SEARCH PATIENT");
		System.out.println("1. Search by ID");
		System.out.println("2. Search by Name");
		System.out.println("3. Search by Phone Number");
		System.out.print("Choose search method (1-3): ");

		while (true) {
			String choice = scanner.nextLine();

			if (Utilities.isQuitChoice(choice)) {
				System.out.println("Exiting edit mode.");
				return;
			}

			switch (choice) {
			case "1":
				System.out.print("Enter Patient ID: ");
				String id = scanner.nextLine();
				if (this.patientStore.hasPerson(id)) {
					System.out.println(this.patientStore.getPerson(id));
				} else {
					System.out.println("Patient with ID " + id + " not found.");
				}
				return;
			case "2": {
				System.out.print("Enter Patient Name: ");
				String name = scanner.nextLine();
				var allPatients = this.patientStore.getAllPersons();

				List<Person> foundPatients = allPatients.values().stream()
						.filter(p -> p.getName().equalsIgnoreCase(name)).toList();

				if (foundPatients.isEmpty()) {
					System.out.println("No patients found with name " + name);
				} else {
					for (Person p : foundPatients) {
						System.out.println(p);
					}
				}
				return;
			}
			case "3": {
				System.out.print("Enter Patient Phone Number: ");
				String phoneNumber = scanner.nextLine();

				var allPatients = this.patientStore.getAllPersons();
				
				List<Person> foundPatients = allPatients.values().stream()
						.filter(p -> p.getPhoneNumber().equalsIgnoreCase(phoneNumber)).toList();
				
				if (foundPatients.isEmpty()) {
					System.out.println("No patients found with phone number " + phoneNumber);
				} else {
					for (Person p : foundPatients) {
						System.out.println(p);
					}
				}
				return;
			}
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}

	// Adds a new doctor
	public void addDoctor(Scanner scanner) {
		System.out.print("Enter Doctor Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Doctor Specialty: ");
		String specialty = scanner.nextLine();

		System.out.print("Enter Doctor Phone Number: ");
		String phoneNumber = scanner.nextLine();

		int newId = this.doctorStore.getPersonCount() + 1; // Simple ID generation
		Doctor newDoctor = new Doctor(String.valueOf(newId), name, specialty, phoneNumber);

		this.doctorStore.addDoctor(newDoctor);
		System.out.println("Doctor added successfully: " + newDoctor);
	}

	// Removes a doctor
	public void deleteDoctor(Scanner scanner) {
		System.out.println("Delete Doctor: Doctor has left the chat (and the practice or the province)");
	}

	// Assigns a patient to a doctor
	public void assignPatientToDoctor(Scanner scanner) {
		System.out.println("Assign Patient to Doctor");
	}

	// Manually input a prescription. Yes, everything. No shortcuts. Hope you like
	// typing! xD
	public void acceptPrescription(Scanner scanner) {
		System.out.println("Accept Prescription");
	}

	// Restocks every med with a random quantity. Totally how pharmacies work in
	// real life.... yup totally
	public void restockDrugs() {
		System.out.println("Restock Drugs");
	}

	// Prints every doctor, patient, med, and prescription. yeah it's a wall of
	// text...
	public void generateFullReport() {
		System.out.println("\n--- SYSTEM REPORT ---");

		System.out.println("Doctors:");
		for (Doctor doc : doctors) {
			System.out.println(doc);
		}

		System.out.println("\nPatients:");
		for (Patient p : patients) {
			System.out.println(p);
		}

		System.out.println("\nMedications:");
		for (Medication m : medications) {
			System.out.println(m);
		}

		System.out.println("\nPrescriptions:");
		for (Prescription pr : prescriptions) {
			System.out.println(pr);
		}
	}

	// Loops through meds. Prints the expired ones. You’ll act shocked even though
	// no one updates the inventory
	public void checkExpiredMedications() {
		System.out.println("\n--- EXPIRED MEDICATIONS ---");
		boolean found = false;
		for (Medication m : medications) {
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
		for (Prescription p : prescriptions) {
			if (p.getDoctor().getName().equalsIgnoreCase(name)) {
				System.out.println(p);
			}
		}
	}

	// Lists all drugs prescribed to a patient in the past year
	public void printPatientDrugSummary(Scanner scanner) {
		System.out.print("Enter patient name: ");
		String name = scanner.nextLine();

		for (Patient p : patients) {
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

	public void editDoctor(Scanner scanner) {
		System.out.println("Edit Doctor: Not implemented yet.");
	}

	public void searchDoctor(Scanner scanner) {
		System.out.println("Search Doctor: Not implemented yet.");
	}

	public void addMedication(Scanner scanner) {
		System.out.println("Add Medication: Not implemented yet.");
	}

	public void deleteMedication(Scanner scanner) {
		System.out.println("Delete Medication: Not implemented yet.");
	}

	public void editMedication(Scanner scanner) {
		System.out.println("Edit Medication: Not implemented yet.");
	}

	public void searchMedication(Scanner scanner) {
		System.out.println("Search Medication: Not implemented yet.");
	}

}
