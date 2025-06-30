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

    public String getName() {
        return name;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Phone: " + phoneNumber;
    }
}