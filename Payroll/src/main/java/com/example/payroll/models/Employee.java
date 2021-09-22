package com.example.payroll.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Locale;
import java.util.Objects;

@Entity
public class Employee {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String role;

    private String firstName;

    private String lastName;

    Employee() {
    }


    public Employee(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
    public Employee(String name,String role){
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];

        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String first) {
        this.firstName = first;
    }

    public void setLastName(String last) {
        this.lastName = last;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public String toString() {
        return "Employee{" + " id=" + this.id + ", name=" + this.name + ", role=" + this.role + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }
}
