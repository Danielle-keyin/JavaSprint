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
        Doctor doctor1 = new Doctor("D001", "Dr. Smith", 45, "555-1234", "General Medicine");
        Doctor doctor2 = new Doctor("D002", "Dr. Jones", 50, "555-5678", "Cardiology");

        // Add doctors to system
        system.getDoctors().put(doctor1.getId(), doctor1);
        system.getDoctors().put(doctor2.getId(), doctor2);

        // Create Patients
        Patient patient1 = new Patient("P001", "Alice Johnson", 35, "555-2222");
        Patient patient2 = new Patient("P002", "Bob Williams", 42, "555-3333");

        // Add patients to system via PatientStore
        system.getPatientStore().addPatient(patient1);
        system.getPatientStore().addPatient(patient2);

        // Create Medications
        Medication med1 = new Medication(
            "M001",
            "Amoxicillin",
            "500mg",
            100,
            LocalDate.now().plusMonths(6)
        );

        Medication med2 = new Medication(
            "M002",
            "Expired Ibuprofen",
            "200mg",
            50,
            LocalDate.now().minusDays(30)
        );

        // Add medications to system
        system.getMedications().put(med1.getId(), med1);
        system.getMedications().put(med2.getId(), med2);

        // Create Prescriptions
        Prescription pr1 = new Prescription(doctor1, med1, LocalDate.now().minusMonths(2));
        Prescription pr2 = new Prescription(doctor2, med2, LocalDate.now().minusYears(2));

        // Add prescriptions to patients
        patient1.addPrescription(pr1);
        patient2.addPrescription(pr2);

        // Add prescriptions to system
        system.getPrescriptions().add(pr1);
        system.getPrescriptions().add(pr2);

        System.out.println("Sample data populated!");
    }
}
