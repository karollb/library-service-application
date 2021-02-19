package pl.edu.wszib.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.model.Customer;
import pl.edu.wszib.library.services.ICustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerDAO customerDAO;

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerDAO.getAllCustomers();
    }

    @Override
    public Customer getCustomerByPeselNumber(String peselNumber) {
        return this.customerDAO.getCustomerByPeselNumber(peselNumber);
    }

    @Override
    public boolean addNewCustomer(Customer customer) {
        if (this.customerDAO.getCustomerByPeselNumber(customer.getPeselNumber()) != null) {
            return false;
        }
        return this.customerDAO.addNewCustomer(customer);
    }
}
