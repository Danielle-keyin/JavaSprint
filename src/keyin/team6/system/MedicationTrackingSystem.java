package keyin.team6.system;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import keyin.team6.model.Doctor;
import keyin.team6.model.Medication;
import keyin.team6.model.Patient;
import keyin.team6.model.Prescription;
import keyin.team6.utils.Utilities;

public class MedicationTrackingSystem {

	public PersonStore<Patient> patientStore;
	public DoctorStore doctorStore;
	private Map<String, Medication> medications;
	private List<Prescription> prescriptions;

	public MedicationTrackingSystem() {
		this.patientStore = new PersonStore<>();
		this.doctorStore = new DoctorStore();
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

				if (newId.isEmpty()) {
					System.out.println("No ID provided. Cancelling.");
					continue; // Prompt again
				}

				this.patientStore.changePersonId(patient.getId(), newId);
				System.out.println("Patient ID updated to " + newId);
				return;
			case "2":
				System.out.print("Enter new Name: ");
				String newName = scanner.nextLine();

				if (newName.isEmpty()) {
					System.out.println("No name provided. Cancelling.");
					continue; // Prompt again
				}
				patient.setName(newName);
				System.out.println("Patient name updated to " + newName);
				return;
			case "3":
				System.out.print("Enter new Age: ");
				String ageInput = scanner.nextLine();

				if (ageInput.isEmpty()) {
					System.out.println("No age provided. Cancelling.");
					continue; // Prompt again
				}

				int newAge = Integer.parseInt(scanner.nextLine());

				if (newAge < 0) {
					System.out.println("Age cannot be negative. Please try again.");
					continue; // Prompt again
				}
				patient.setAge(newAge);
				System.out.println("Patient age updated to " + newAge);
				return;
			case "4":
				System.out.print("Enter new Phone Number: ");
				String newPhoneNumber = scanner.nextLine();
				
				
				if (newPhoneNumber.isEmpty()) {
					System.out.println("No phone number provided. Cancelling.");
					continue; // Prompt again
				}
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
				if (!this.patientStore.hasPerson(id)) {
					System.out.println("Patient with ID " + id + " not found.");
					return;
				}

				System.out.println(this.patientStore.getPerson(id));
				return;
			case "2": {
				System.out.print("Enter Patient Name: ");
				String name = scanner.nextLine();
				var allPatients = this.patientStore.getAllPersons();

				List<Patient> foundPatients = allPatients.values().stream()
						.filter(p -> p.getName().toLowerCase().contains(name)).toList();

				if (foundPatients.isEmpty()) {
					System.out.println("No patients found with name " + name);
					return;
				}

				for (Patient p : foundPatients) {
					System.out.println(p);
				}

				return;
			}
			case "3": {
				System.out.print("Enter Patient Phone Number: ");
				String phoneNumber = scanner.nextLine();

				var allPatients = this.patientStore.getAllPersons();

				List<Patient> foundPatients = allPatients.values().stream()
						.filter(p -> p.getPhoneNumber().equalsIgnoreCase(phoneNumber)).toList();

				if (foundPatients.isEmpty()) {
					System.out.println("No patients found with phone number " + phoneNumber);
					return;

				}

				for (Patient p : foundPatients) {
					System.out.println(p);
				}
				return;
			}
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}

	// TODO (for a real company) Refactor the Doctor and Patient methods to avoid
	// code duplication. i could not think of a good way to do it at the time.

	// Adds a new doctor
	public void addDoctor(Scanner scanner) {
		System.out.print("Enter Doctor Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Doctor Age: ");
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

		System.out.print("Enter Doctor Phone Number: ");
		String phoneNumber = scanner.nextLine();

		System.out.print("Enter Doctor Specialty: ");
		String specialty = scanner.nextLine();

		int newId = this.doctorStore.getPersonCount() + 1; // Simple ID generation

		Doctor newDoctor = new Doctor(String.valueOf(newId), name, age, phoneNumber, specialty);

		this.doctorStore.addPerson(newDoctor);
		System.out.println("Doctor added successfully: " + newDoctor);
	}

	// Removes a doctor
	public void deleteDoctor(Scanner scanner) {
		System.out.print("Enter Doctor ID to delete: ");
		String id = scanner.nextLine();

		if (!this.doctorStore.hasPerson(id)) {
			System.out.println("Doctor with ID " + id + " not found.");
			return;
		}

		var doctor = this.doctorStore.getPerson(id);
		System.out.print("Delete '" + doctor.getName() + "'? (yes/no): ");
		String confirmation = scanner.nextLine();
		if (confirmation.equalsIgnoreCase("yes")) {
			this.doctorStore.deletePerson(id);
			System.out.println("Doctor with ID " + id + " has been deleted.");
		} else {
			System.out.println("Deletion cancelled.");
		}
	}

	// Assigns a patient to a doctor
	public void assignPatientToDoctor(Scanner scanner) {
		System.out.print("Enter Patient ID: ");
		String patientId = scanner.nextLine();

		if (!this.patientStore.hasPerson(patientId)) {
			System.out.println("Patient with ID " + patientId + " not found.");
			return;
		}

		System.out.print("Enter Doctor ID: ");
		String doctorId = scanner.nextLine();

		if (!this.doctorStore.hasPerson(doctorId)) {
			System.out.println("Doctor with ID " + doctorId + " not found.");
			return;
		}

		var patient = this.patientStore.getPerson(patientId);
		var doctor = this.doctorStore.getPerson(doctorId);

		doctor.addPatient(patient);
		// patient.setDoctor(doctor); // should patient have a reference to doctor?

		System.out.println("Patient '" + patient.getName() + "' assigned to Doctor '" + doctor.getName() + "'.");
	}

	public void editDoctor(Scanner scanner) {
		System.out.print("Enter Doctor ID to edit: ");
		String id = scanner.nextLine();

		if (!this.doctorStore.hasPerson(id)) {
			System.out.println("Doctor with ID " + id + " not found.");
			return;
		}

		var doctor = this.doctorStore.getPerson(id);
		System.out.println("Editing Doctor: " + doctor.getName());
		System.out.println("1. ID (current: " + doctor.getId() + ")");
		System.out.println("2. NAME (current: " + doctor.getName() + ")");
		System.out.println("3. AGE (current: " + doctor.getAge() + ")");
		System.out.println("4. PHONE NUMBER (current: " + doctor.getPhoneNumber() + ")");
		System.out.println("5. SPECIALIZATION (current: " + doctor.getSpecialization() + ")");
		System.out.print("Choose field to edit (1-5) or 0 to exit: ");

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
				this.doctorStore.changePersonId(doctor.getId(), newId);
				System.out.println("Doctor ID updated to " + newId);
				return;
			case "2":
				System.out.print("Enter new Name: ");
				String newName = scanner.nextLine();
				this.doctorStore.changePersonName(doctor.getId(), newName);
				System.out.println("Doctor name updated to " + newName);
				return;
			case "3":
				System.out.print("Enter new Age: ");
				int newAge = Integer.parseInt(scanner.nextLine());
				this.doctorStore.changePersonAge(doctor.getId(), newAge);
				System.out.println("Doctor age updated to " + newAge);
				return;
			case "4":
				System.out.print("Enter new Phone Number: ");
				String newPhoneNumber = scanner.nextLine();
				this.doctorStore.changePersonPhoneNumber(doctor.getId(), newPhoneNumber);
				System.out.println("Doctor phone number updated to " + newPhoneNumber);
				return;
			case "5":
				System.out.print("Enter new Specialization: ");
				String newSpecialization = scanner.nextLine();
				this.doctorStore.changeDoctorSpecialization(doctor.getId(), newSpecialization);
				System.out.println("Doctor specialization updated to " + newSpecialization);
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public void searchDoctor(Scanner scanner) {
		System.out.println("SEARCH DOCTOR");
		System.out.println("1. Search by ID");
		System.out.println("2. Search by Name");
		System.out.println("3. Search by Phone Number");
		System.out.println("4. Search by Specialty");
		System.out.print("Choose search method (1-3): ");

		while (true) {
			String choice = scanner.nextLine();

			if (Utilities.isQuitChoice(choice)) {
				System.out.println("Exiting search mode.");
				return;
			}

			switch (choice) {
			case "1":
				System.out.print("Enter Doctor ID: ");
				String id = scanner.nextLine();
				if (this.doctorStore.hasPerson(id)) {
					System.out.println("Doctor with ID " + id + " not found.");
				}

				System.out.println(this.doctorStore.getPerson(id));
				return;
			case "2": {
				System.out.print("Enter Doctor Name: ");
				String name = scanner.nextLine();
				var allDoctors = this.doctorStore.getAllPersons();

				List<Doctor> foundDoctors = allDoctors.values().stream().filter(d -> d.getName().toLowerCase().contains(name))
						.toList();

				if (foundDoctors.isEmpty()) {
					System.out.println("No doctors found with name " + name);
					return;
				}

				for (Doctor d : foundDoctors) {
					System.out.println(d);
				}

				return;
			}
			case "3": {
				System.out.print("Enter Doctor Phone Number: ");
				String phoneNumber = scanner.nextLine();

				var allDoctors = this.doctorStore.getAllPersons();

				List<Doctor> foundDoctors = allDoctors.values().stream()
						.filter(d -> d.getPhoneNumber().equalsIgnoreCase(phoneNumber)).toList();

				if (foundDoctors.isEmpty()) {
					System.out.println("No doctors found with phone number " + phoneNumber);
					return;
				}

				for (Doctor d : foundDoctors) {
					System.out.println(d);
				}

				return;
			}
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public void addMedication(Scanner scanner) {
		System.out.print("Enter Medication Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Medication Dose: ");
		String dose = scanner.nextLine();

		System.out.print("Enter Medication Quantity in Stock: ");
		int quantityInStock = -1;
		while (true) {
			try {
				quantityInStock = Integer.parseInt(scanner.nextLine());
				if (quantityInStock < 0) {
					System.out.print("Quantity cannot be negative. Please enter a valid quantity: ");
					continue; // Prompt again
				}
				break;
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a valid quantity: ");
				continue; // Prompt again
			}
		}

		LocalDate expiryDate = LocalDate.now().plusYears(1); // Default expiry date, can be changed later

		System.out.print("Enter Medication Expiry Date (YYYY-MM-DD) or leave blank for 1 year: ");
		String expiryInput = scanner.nextLine();
		while (true) {
			if (!expiryInput.isEmpty()) {
				try {
					expiryDate = LocalDate.parse(expiryInput);
					break;
				} catch (Exception e) {
					System.out.println(
							"Invalid date format. Please enter a valid date (YYYY-MM-DD) or blank for 1 year: ");
				}
			}
		}

		int newId = this.medications.size() + 1; // Simple ID generation

		Medication newMedication = new Medication(String.valueOf(newId), name, dose, quantityInStock, expiryDate);
		this.medications.put(String.valueOf(newId), newMedication);
	}

	public void deleteMedication(Scanner scanner) {
		System.out.print("Enter Medication ID to delete: ");
		String id = scanner.nextLine();

		if (!this.medications.containsKey(id)) {
			System.out.println("Medication with ID " + id + " not found.");
			return;
		}

		// TODO (for a real company) check if any prescriptions are linked to this
		// medication

		var medication = this.medications.get(id);
		System.out.print("Delete '" + medication.getName() + "'? (yes/no): ");
		String confirmation = scanner.nextLine();
		if (confirmation.equalsIgnoreCase("yes")) {
			this.medications.remove(id);
			System.out.println("Medication with ID " + id + " has been deleted.");
		} else {
			System.out.println("Deletion cancelled.");
		}
	}

	public void editMedication(Scanner scanner) {
		System.out.print("Enter Medication ID to edit: ");
		String id = scanner.nextLine();

		if (!this.medications.containsKey(id)) {
			System.out.println("Medication with ID " + id + " not found.");
			return;
		}

		var medication = this.medications.get(id);
		System.out.println("Editing Medication: " + medication.getName());
		System.out.println("1. ID (current: " + medication.getId() + ")");
		System.out.println("2. NAME (current: " + medication.getName() + ")");
		System.out.println("3. DOSE (current: " + medication.getDose() + ")");
		System.out.println("4. QUANTITY IN STOCK (current: " + medication.getQuantityInStock() + ")");
		System.out.println("5. EXPIRY DATE (current: " + medication.getExpiryDate() + ")");
		System.out.print("Choose field to edit (1-5) or 0 to exit: ");

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

				if (newId.isEmpty()) {
					System.out.println("No ID provided. Cancelling.");
					continue; // Prompt again
				}

				this.medications.remove(medication.getId());
				medication.setId(newId);
				this.medications.put(newId, medication);
				System.out.println("Medication ID updated to " + newId);
				return;
			case "2":
				System.out.print("Enter new Name: ");
				String newName = scanner.nextLine();

				if (newName.isEmpty()) {
					System.out.println("No name provided. Cancelling.");
					continue; // Prompt again
				}

				medication.setName(newName);
				System.out.println("Medication name updated to " + newName);
				return;
			case "3":
				System.out.print("Enter new Dose: ");
				String newDose = scanner.nextLine();

				if (newDose.isEmpty()) {
					System.out.println("No dose provided. Cancelling.");
					continue; // Prompt again
				}

				medication.setDose(newDose);
				System.out.println("Medication dose updated to " + newDose);
				return;
			case "4":
				System.out.print("Enter new Quantity in Stock: ");
				String quantityInput = scanner.nextLine();

				if (quantityInput.isEmpty()) {
					System.out.println("No quantity provided. Cancelling.");
					continue; // Prompt again
				}

				int newQuantity = Integer.parseInt(quantityInput);
				if (newQuantity < 0) {
					System.out.println("Quantity cannot be negative. Please try again.");
					continue; // Prompt again
				}
				medication.restock(newQuantity - medication.getQuantityInStock());
				System.out.println("Medication quantity updated to " + medication.getQuantityInStock());
				return;
			case "5":
				System.out.print("Enter new Expiry Date (YYYY-MM-DD): ");
				String expiryInput = scanner.nextLine();

				if (expiryInput.isEmpty()) {
					System.out.println("No expiry date provided. Cancelling.");
					continue; // Prompt again
				}

				LocalDate newExpiryDate;

				try {
					newExpiryDate = LocalDate.parse(expiryInput);
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date format. Please try again.");
					continue; // Prompt again
				}

				medication.setExpiryDate(newExpiryDate);
				System.out.println("Medication expiry date updated to " + newExpiryDate);
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
				break;
			}
		}
	}

	public void searchMedication(Scanner scanner) {
		System.out.println("SEARCH MEDICATION");
		System.out.println("1. Search by ID");
		System.out.println("2. Search by Name");
		System.out.print("Choose search method (1-2): ");

		while (true) {
			String choice = scanner.nextLine();

			if (Utilities.isQuitChoice(choice)) {
				System.out.println("Exiting search mode.");
				return;
			}

			switch (choice) {
			case "1":
				System.out.print("Enter Medication ID: ");
				String id = scanner.nextLine();
				if (!this.medications.containsKey(id)) {
					System.out.println("Medication with ID " + id + " not found.");
					return;
				}

				System.out.println(this.medications.get(id));
				return;
			case "2": {
				System.out.print("Enter Medication Name: ");
				String name = scanner.nextLine();

				List<Medication> foundMeds = new ArrayList<>();
				for (Medication m : this.medications.values()) {
					if (m.getName().toLowerCase().contains(name.toLowerCase())) {
						foundMeds.add(m);
					}
				}

				if (foundMeds.isEmpty()) {
					System.out.println("No medications found with name " + name);
					return;
				}

				for (Medication m : foundMeds) {
					System.out.println(m);
				}

				return;
			}
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	// Manually input a prescription. Yes, everything. No shortcuts. Hope you like typing! xD
	public void acceptPrescription(Scanner scanner) {
		Patient patient;
	
		while (true) {
			System.out.print("Enter Patient ID: ");
			String patientId = scanner.nextLine();

			if (!this.patientStore.hasPerson(patientId)) {
				System.out.println("Patient with ID " + patientId + " not found.");
				continue; 
			}

			patient = this.patientStore.getPerson(patientId);
			break;
		}

		Doctor doctor;
		
		while (true) {
			System.out.print("Enter Doctor ID: ");
			String doctorId = scanner.nextLine();

			if (!this.doctorStore.hasPerson(doctorId)) {
				System.out.println("Doctor with ID " + doctorId + " not found.");
				continue; 
			}

			doctor = this.doctorStore.getPerson(doctorId);
			break;
		}

		Medication medication;
		
		while (true) {
			System.out.print("Enter Medication ID: ");
			String medicationId = scanner.nextLine();

			if (!this.medications.containsKey(medicationId)) {
				System.out.println("Medication with ID " + medicationId + " not found.");
				continue; 
			}

			medication = this.medications.get(medicationId);
			if (medication.getQuantityInStock() <= 0) {
				System.out.println("Medication is out of stock. Are you sure? (yes/no)");
				String confirmation = scanner.nextLine();
				if (!confirmation.equalsIgnoreCase("yes")) {
					continue;
				} 
			}
			break;
		}

		LocalDate issueDate = LocalDate.now();

		Prescription newPrescription = new Prescription(patient, doctor, medication, issueDate);
		
		// TODO (for a real company) check if the patient already has a prescription for this medication
		
		this.prescriptions.add(newPrescription);
		patient.addPrescription(newPrescription);
		
		System.out.println("Prescription added successfully: " + newPrescription);
	}

	// Restocks every med with a random quantity. Totally how pharmacies work in real life.... yup totally
	public void restockDrugs() {
		System.out.println("\n--- RESTOCKING MEDICATIONS ---");
		for (Medication m : this.medications.values()) {
			int restockAmount = (int) (Math.random() * 50) + 1; // Random restock amount between 1 and 50
			m.restock(restockAmount);
			System.out.println("Restocked " + m.getName() + " by " + restockAmount + ". New stock: " + m.getQuantityInStock());
		}
		System.out.println("All medications have been restocked.");
	}
	
	public Map<String, Medication> getMedications() {
		return new HashMap<>(this.medications);
	}
	
	public List<Prescription> getPrescriptions() {
		return new ArrayList<>(this.prescriptions);
	}
	
	public void insertDoctor(Doctor doctor) {
		this.doctorStore.addPerson(doctor);
	}
	
	public void insertPatient(Patient patient) {
		this.patientStore.addPerson(patient);
	}
	
	public void insertMedication(Medication medication) {
		this.medications.put(medication.getId(), medication);
	}
	
	public void insertPrescription(Prescription prescription) {
		this.prescriptions.add(prescription);
	}

}
