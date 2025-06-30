//initialize doctor-specific and inherited fields , add a patient to the doctor's list
//and output doctor info and number of patients
package keyin.team6.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
    private String specialization;
    private List<Patient> patients;

    public Doctor(String id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization + ", Patients: " + patients.size();
    }
}