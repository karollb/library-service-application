package pl.edu.wszib.library.model;

import javax.persistence.*;

@Entity(name = "tcustomer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 25)
    private String firstName;
    @Column(length = 25)
    private String surname;
    @Column(length = 11)
    private String peselNumber;

    public Customer() {
    }

    public Customer(int id, String firstName, String surname, String peselNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }
}
