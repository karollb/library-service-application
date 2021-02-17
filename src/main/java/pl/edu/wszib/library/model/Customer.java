package pl.edu.wszib.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tcustomer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String surName;
    private int peselNumber;

    public Customer() {
    }

    public Customer(int id, String firstName, String surName, int peselNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.peselNumber = peselNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(int peselNumber) {
        this.peselNumber = peselNumber;
    }
}
