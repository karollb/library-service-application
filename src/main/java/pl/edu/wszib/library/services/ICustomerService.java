package pl.edu.wszib.library.services;

import pl.edu.wszib.library.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomerByPeselNumber(String peselNumber);

    boolean addNewCustomer(Customer customer);
}
