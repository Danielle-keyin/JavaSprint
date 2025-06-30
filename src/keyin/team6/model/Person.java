package keyin.team6.model;

public class Person {
    protected String id;
    protected String name;
    protected int age;
    protected String phoneNumber;

    public Person(String id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
		return this.id;
	}
    
    public String getName() {
        return this.name;
    }
    
    public int getAge() {
		return this.age;
	}
    
    public String getPhoneNumber() {
		return this.phoneNumber;
	}
    
    public void setId(String id) {
		this.id = id;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setAge(int age) {
    	this.age = age;
    }
    
    public void setPhoneNumber(String phoneNumber) {
    	this.phoneNumber = phoneNumber;
    }
    

    public String toString() {
        return "ID: " + this.id + ", Name: " + this.name + ", Age: " + this.age + ", Phone: " + this.phoneNumber;
    }
}