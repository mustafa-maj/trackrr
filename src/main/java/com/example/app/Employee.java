package com.example.app;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    private final SimpleIntegerProperty employeeID;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty position;
    private final SimpleStringProperty gender;
    private final SimpleIntegerProperty phone;
    private final SimpleStringProperty email;

    public Employee(int employeeID, String firstName, String lastName, String position, String gender, Integer phone, String email) {
        this.employeeID = new SimpleIntegerProperty(employeeID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.position = new SimpleStringProperty(position);
        this.gender = new SimpleStringProperty(gender);
        this.phone = new SimpleIntegerProperty(phone);
        this.email = new SimpleStringProperty(email);
    }

    // Getters and setters for all fields
    public int getEmployeeID() { return employeeID.get(); }
    public void setEmployeeID(int employeeID) { this.employeeID.set(employeeID); }

    public String getFirstName() { return firstName.get(); }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }

    public String getLastName() { return lastName.get(); }
    public void setLastName(String lastName) { this.lastName.set(lastName); }

    public String getPosition() { return position.get(); }
    public void setPosition(String position) { this.position.set(position); }

    public String getGender() { return gender.get(); }
    public void setGender(String gender) { this.gender.set(gender); }

    public Integer getPhone() { return phone.get(); }
    public void setPhone(Integer phone) { this.phone.set(phone); }

    public String getEmail() { return email.get(); }
    public void setEmail(String email) { this.email.set(email); }
}
