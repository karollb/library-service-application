package pl.edu.wszib.library.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tloan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    @Temporal(value = TemporalType.DATE)
    private Date date;

    public Loan() {
    }

    public Loan(int id, Customer customer, Book book, Date date) {
        this.id = id;
        this.customer = customer;
        this.book = book;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
