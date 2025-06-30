package keyin.team6.model;

import java.time.LocalDate;
import java.util.UUID;

public class ModelTest {
    public static void main(String[] args) {
        // Create a doctor
        Doctor doctor = new Doctor("D001", "Dr. Alice", 45, "709-555-1234", "Cardiology");

        // Create a patient
        Patient patient = new Patient("P001", "Bob", 30, "709-555-5678");

        // Assign patient to doctor
        doctor.addPatient(patient);

        // Create a medication with fixed objects (will be changed after)
        Medication med = new Medication(UUID.randomUUID().toString(), "Ibuprofen", "200mg", 100, LocalDate.of(2025, 12, 1));

        // Create a prescription with a prescribed doctor , medication and date of prescription 
        Prescription prescription = new Prescription(patient, doctor, med, LocalDate.now());

        // Add prescription to patient
        patient.addPrescription(prescription);

        // Output test results
        System.out.println(doctor);
        System.out.println(patient);
        System.out.println(med);
        System.out.println("Prescribed by: " + prescription.getDoctor().getName());
    }
}


// test the model layer of Pharmacy Management system , links Doctor , Patient , Medication  and Prescription together 
// and prints the output to validate object behavior .