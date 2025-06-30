package keyin.team6.utils;

import keyin.team6.model.Doctor;
import keyin.team6.model.Patient;
import keyin.team6.model.Medication;
import keyin.team6.model.Prescription;
import keyin.team6.system.MedicationTrackingSystem;

import java.time.LocalDate;

public class SampleDataGenerator {

    public static void populateSampleData(MedicationTrackingSystem system) {
        // Create Doctors
        Doctor doctor1 = new Doctor("Dr. Smith", "General Medicine");
        Doctor doctor2 = new Doctor("Dr. Jones", "Cardiology");

        // Add doctors
        system.getDoctors().add(doctor1);
        system.getDoctors().add(doctor2);

        // Create Patients
        Patient patient1 = new Patient("Alice Johnson");
        Patient patient2 = new Patient("Bob Williams");

        // Add patients
        system.getPatients().add(patient1);
        system.getPatients().add(patient2);

        // Create Medications
        Medication med1 = new Medication("Amoxicillin", LocalDate.now().plusMonths(6));
        Medication med2 = new Medication("Expired Ibuprofen", LocalDate.now().minusDays(30));

        // Add medications
        system.getMedications().add(med1);
        system.getMedications().add(med2);

        // Create Prescriptions
        Prescription pr1 = new Prescription(doctor1, med1, LocalDate.now().minusMonths(2));
        Prescription pr2 = new Prescription(doctor2, med2, LocalDate.now().minusYears(2));

        // Add prescriptions to patients
        patient1.getPrescriptions().add(pr1);
        patient2.getPrescriptions().add(pr2);

        // Add prescriptions to system
        system.getPrescriptions().add(pr1);
        system.getPrescriptions().add(pr2);

        System.out.println("Sample data populated!");
    }
}
