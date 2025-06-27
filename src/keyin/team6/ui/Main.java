// I (Danielle) have worked in a pharmacy for the past 16 years so pardon the frustration in the pt / doc interactions in the menulol
package keyin.team6.ui;

// We import the system. Because everything depends on this one class. Of course it does.
import keyin.team6.system.MedicationTrackingSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create the system. It's empty. You have to manually fill it. Yay.
        MedicationTrackingSystem system = new MedicationTrackingSystem();

        // Scanner to read user input. Get ready to type. A lot.
        Scanner scanner = new Scanner(System.in);

        // Controls the menu loop. Never-ending joy.
        boolean running = true;

        // Loop until user gives up and exits.
        while (running) {
            printMenu(); // Show all the options. Again.
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            // Handle the choice
            switch (choice) {
                case "1" -> system.addPatient(scanner);              // Add a patient. Basic
                case "2" -> system.deletePatient(scanner);           // Delete them. Gone
                case "3" -> system.editPatient(scanner);             // Fix the typo you made
                case "4" -> system.searchPatient(scanner);           // Hope you spelled it right
                case "5" -> system.addDoctor(scanner);               // Doctors join the chaos
                case "6" -> system.deleteDoctor(scanner);            // They're out
                case "7" -> system.editDoctor(scanner);              // Change their name. Again
                case "8" -> system.searchDoctor(scanner);            // Another guessing game
                case "9" -> system.addMedication(scanner);           // Add drugs. Legally
                case "10" -> system.deleteMedication(scanner);       // Oops, didn’t need that one
                case "11" -> system.editMedication(scanner);         // Dosage was wrong? Shocking
                case "12" -> system.searchMedication(scanner);       // “Tylenol” or “Tylonel”? Good luck
                case "13" -> system.assignPatientToDoctor(scanner);  // Force a relationship. Why not
                case "14" -> system.acceptPrescription(scanner);     // Enter everything manually. Fun
                case "15" -> system.generateFullReport();            // Dump all the data. Wall of text incoming
                case "16" -> system.checkExpiredMedications();       // Wow, stuff expired. Surprise
                case "17" -> system.printPrescriptionsByDoctor(scanner); // The doctor writes *how* many?
                case "18" -> system.printPatientDrugSummary(scanner);    // Here's what the patient hoarded all year
                case "19" -> system.restockDrugs();                      // Random refill. Totally realistic..
                case "0" -> {
                    // Finally, sweet escape.
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Of course.");
            }
        }

        // Close the scanner. It deserves it.
        scanner.close();
    }

    private static void printMenu() {
        // The menu. All 19 glorious options. Because 5 wasn’t enough
        System.out.println("\n=== PHARMACY MANAGEMENT MENU ===");
        System.out.println("1. Add Patient");
        System.out.println("2. Delete Patient");
        System.out.println("3. Edit Patient");
        System.out.println("4. Search for Patient");
        System.out.println("5. Add Doctor");
        System.out.println("6. Delete Doctor");
        System.out.println("7. Edit Doctor");
        System.out.println("8. Search for Doctor");
        System.out.println("9. Add Medication");
        System.out.println("10. Delete Medication");
        System.out.println("11. Edit Medication");
        System.out.println("12. Search for Medication");
        System.out.println("13. Assign Patient to Doctor");
        System.out.println("14. Accept Prescription");
        System.out.println("15. Generate Full Report");
        System.out.println("16. Check Expired Medications");
        System.out.println("17. Print Prescriptions by Doctor");
        System.out.println("18. Print Patient Drug Summary (Past Year)");
        System.out.println("19. Restock Medications");
        System.out.println("0. Exit");
    }
}
