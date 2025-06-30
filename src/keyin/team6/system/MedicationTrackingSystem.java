package keyin.team6.system;

import keyin.team6.keyin.team6.model.Doctor;  //Here is Artem u've got path keyin.team6.keyin.team6 (twice) so I changed it
import keyin.team6.model.Patient;
import keyin.team6.model.Medication;
import keyin.team6.model.Prescription;
import keyin.team6.model.Doctor;           //Artem* actually I just added it here because when I removing the first import 
                                        // java.util.Random import not exists 

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MedicationTrackingSystem {

    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Medication> medications;
    private List<Prescription> prescriptions;

    public MedicationTrackingSystem() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }
    
    public List<Doctor> getDoctors() {
    return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }


    // Adds a new patient. Yes, manually. No, there's no form. Welcome to 2003 bahahaha
    public void addPatient(Scanner scanner) {
        System.out.println("Add Patient: Not implemented yet");
    }

    // Deletes a patient from the system
    public void deletePatient(Scanner scanner) {
        System.out.println("Delete Patient: Patient mysteriously vanished. Probably moved pharmacies");
    }

    // Because someone *definitely* entered "Jhn Smth" by accident. Time to fix it
    public void editPatient(Scanner scanner) {
        System.out.println("Edit Patient: Fixing typos one at a time, because why use validation? haha");
    }

    // Search for a patient. If you get the name even slightly wrong, oh well
    public void searchPatient(Scanner scanner) {
        System.out.println("Search Patient: Good luck spelling it right on the first try");
    }

    // Adds a new doctor
    public void addDoctor(Scanner scanner) {
        System.out.println("Add Doctor: Added a new doc");
    }

    // Removes a doctor
    public void deleteDoctor(Scanner scanner) {
        System.out.println("Delete Doctor: Doctor has left the chat (and the practice or the province)");
    }

    // Assigns a patient to a doctor
    public void assignPatientToDoctor(Scanner scanner) {
        System.out.println("Assign Patient to Doctor");
    }

    // Manually input a prescription. Yes, everything. No shortcuts. Hope you like typing! xD
    public void acceptPrescription(Scanner scanner) {
        System.out.println("Accept Prescription");
    }

    // Restocks every med with a random quantity. Totally how pharmacies work in real life.... yup totally
    public void restockDrugs() {
        System.out.println("Restock Drugs");
    }

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

    // Loops through meds. Prints the expired ones. You’ll act shocked even though no one updates the inventory
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
