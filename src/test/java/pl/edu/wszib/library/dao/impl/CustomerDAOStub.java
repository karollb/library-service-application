package pl.edu.wszib.library.dao.impl;

import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.model.Customer;

import java.util.List;

public class CustomerDAOStub implements ICustomerDAO {
    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getCustomerByPeselNumber(String peselNumber) {
        return null;
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        return false;
    }
}
