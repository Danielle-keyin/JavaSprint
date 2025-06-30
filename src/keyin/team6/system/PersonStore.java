package keyin.team6.system;
import java.util.HashMap;

import keyin.team6.model.Person;

public class PersonStore<T extends Person> {

	protected HashMap<String, T> persons;
	
	public PersonStore() {
		this.persons = new HashMap<>();
	}
	
	public void addPerson(T person) {
		if (person != null && !this.persons.containsKey(person.getId())) {
			this.persons.put(person.getId(), person);
		} else {
			throw new IllegalArgumentException("Person is null or already exists.");
		}
	}
	
	public void deletePerson(String id) {
		if (this.persons.containsKey(id)) {
			this.persons.remove(id);
		} else {
			throw new IllegalArgumentException("Person with ID " + id + " does not exist.");
		}
	}
	
	public boolean hasPerson(String id) {
		return this.persons.containsKey(id);
	}
	
	public T getPerson(String id) {
		if (!this.persons.containsKey(id)) {
			throw new IllegalArgumentException("Person with ID " + id + " does not exist.");
		} 
		
		return this.persons.get(id);
	}
	
	public HashMap<String, T> getAllPersons() {
		return new HashMap<>(this.persons);
	}
	
	public int getPersonCount() {
		return this.persons.size();
	}
	
	public void changePersonId(String oldId, String newId) {
		if (!this.persons.containsKey(oldId)) {
			throw new IllegalArgumentException("Person with ID " + oldId + " does not exist.");
		}
		
		if (this.persons.containsKey(newId)) {
			throw new IllegalArgumentException("Person with ID " + newId + " already exists.");
		}
		
		var person = this.persons.remove(oldId);
		person.setId(newId);
		this.persons.put(newId, person);
	}
	
	public void changePersonName(String id, String newName) {
		if (!this.persons.containsKey(id)) {
			throw new IllegalArgumentException("Person with ID " + id + " does not exist.");
		}
		
		var person = this.persons.get(id);
		person.setName(newName);
		this.persons.put(id, person);
	}
	
	public void changePersonAge(String id, int newAge) {
		if (!this.persons.containsKey(id)) {
			throw new IllegalArgumentException("Person with ID " + id + " does not exist.");
		}
		
		var person = this.persons.get(id);
		person.setAge(newAge);
		this.persons.put(id, person);
	}
	
	public void changePersonPhoneNumber(String id, String newPhoneNumber) {
		if (!this.persons.containsKey(id)) {
			throw new IllegalArgumentException("Person with ID " + id + " does not exist.");
		}
		
		var person = this.persons.get(id);
		person.setPhoneNumber(newPhoneNumber);
		this.persons.put(id, person);
	}
}
