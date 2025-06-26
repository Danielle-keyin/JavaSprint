// Adds a new patient. Yes, manually. No, there's no form. Welcome to 2003 bahahaha
public void addPatient(Scanner scanner) { ... }

// Deletes a patient from the system
public void deletePatient(Scanner scanner) { ... }

// Because someone *definitely* entered "Jhn Smth" by accident. Time to fix it
public void editPatient(Scanner scanner) { ... }

// Search for a patient. If you get the name even slightly wrong, oh well
public void searchPatient(Scanner scanner) { ... }

// Adds a new doctor
public void addDoctor(Scanner scanner) { ... }

// Removes a doctor
public void deleteDoctor(Scanner scanner) { ... }

// Assigns a patient to a doctor
public void assignPatientToDoctor(Scanner scanner) { ... }

// Manually input a prescription. Yes, everything. No shortcuts. Hope you like typing! xD
public void acceptPrescription(Scanner scanner) { ... }

// Prints every doctor, patient, med, and prescription. yeah it's a wall of text...
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

// Loops through meds. Prints the expired ones. You’ll act shocked even though no one updates the inventory (sorry you can sense the personal frustration with pharmacy in my tone lol)
public void checkExpiredMedications() {
    System.out.println("\n--- EXPIRED MEDICATIONS ---");
    boolean found = false;
    for (Medication m : medications) {
        if (m.getExpiryDate().isBefore(LocalDate.now())) {
            System.out.println(m);
            found = true;
        }
    }
    if (!found) System.out.println("No expired medications found. Miraculous.");
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

// Restocks every med with a random quantity. Totally how pharmacies work in real life.... yup totally
public void restockDrugs() { ... }
