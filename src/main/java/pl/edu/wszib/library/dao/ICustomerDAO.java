package pl.edu.wszib.library.dao;

import pl.edu.wszib.library.model.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomers();

    Customer getCustomerByPeselNumber(String peselNumber);

    boolean addNewCustomer(Customer customer);
}
