package keyin.team6.system;

import java.util.HashMap;

import keyin.team6.model.Patient;

public class PatientStore {

	private HashMap<String, Patient> patients;
	
	public PatientStore() {
		this.patients = new HashMap<>();
	}
	
	public void addPatient(Patient patient) {
		if (patient != null && !this.patients.containsKey(patient.getId())) {
			this.patients.put(patient.getId(), patient);
		} else {
			throw new IllegalArgumentException("Patient is null or already exists.");
		}
	}
	
	public void deletePatient(String id) {
		if (this.patients.containsKey(id)) {
			this.patients.remove(id);
		} else {
			throw new IllegalArgumentException("Patient with ID " + id + " does not exist.");
		}
	}
	
	public boolean hasPatient(String id) {
		return this.patients.containsKey(id);
	}
	
	public Patient getPatient(String id) {
		if (!this.patients.containsKey(id)) {
			throw new IllegalArgumentException("Patient with ID " + id + " does not exist.");
		} 
		
		return this.patients.get(id);
	}
	
	public HashMap<String, Patient> getAllPatients() {
		return new HashMap<>(this.patients);
	}
	
	public int getPatientCount() {
		return this.patients.size();
	}
	
	public void changePatientId(String oldId, String newId) {
		if(!this.patients.containsKey(oldId)) {
			throw new IllegalArgumentException("Patient with ID " + oldId + " does not exist.");
		}
		
		
		if(this.patients.containsKey(newId)) {
			throw new IllegalArgumentException("Patient with ID " + newId + " already exists.");
		}
		
		var patient = this.patients.remove(oldId);
		
		var newPatient = new Patient(newId, patient.getName(), patient.getAge(), patient.getPhoneNumber());
		
		var perscriptions = newPatient.getPrescriptions();
		
		for (var prescription : patient.getPrescriptions()) {
			perscriptions.add(prescription);
		}
		
		this.patients.put(newId, newPatient);
	}
	
	public void changePatientName(String id, String newName) {
		if (!this.patients.containsKey(id)) {
			throw new IllegalArgumentException("Patient with ID " + id + " does not exist.");
		}
		
		var patient = this.patients.get(id);
		patient.setName(newName);
		this.patients.put(id, patient);
	}
	
	public void changePatientAge(String id, int newAge) {
		if (!this.patients.containsKey(id)) {
			throw new IllegalArgumentException("Patient with ID " + id + " does not exist.");
		}
		
		var patient = this.patients.get(id);
		patient.setAge(newAge);
		this.patients.put(id, patient);
	}
	
	public void changePatientPhoneNumber(String id, String newPhoneNumber) {
		if (!this.patients.containsKey(id)) {
			throw new IllegalArgumentException("Patient with ID " + id + " does not exist.");
		}
		
		var patient = this.patients.get(id);
		patient.setPhoneNumber(newPhoneNumber);
		this.patients.put(id, patient);
	}
}
