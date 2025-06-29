// add prescription to the patient  and output patient info 
package keyin.team6.model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private List<Prescription> prescriptions;

    public Patient(String id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.prescriptions = new ArrayList<>();
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return super.toString() + ", Medications: " + prescriptions.size();
    }
}