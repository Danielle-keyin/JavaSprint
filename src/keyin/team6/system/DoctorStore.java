package keyin.team6.system;

import keyin.team6.model.Doctor;

public class DoctorStore extends PersonStore<Doctor> {
	
	public DoctorStore() {
		super();
	}

	public void changeDoctorSpecialization(String id, String newSpecialization) {
		if (!this.persons.containsKey(id)) {
			throw new IllegalArgumentException("Doctor with ID " + id + " does not exist.");
		}
		
		var doctor = this.persons.get(id);
		doctor.setSpecialization(newSpecialization);
	}

}
