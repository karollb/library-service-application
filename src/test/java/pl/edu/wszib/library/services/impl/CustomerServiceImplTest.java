package pl.edu.wszib.library.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.library.configuration.TestConfiguration;
import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.model.Customer;
import pl.edu.wszib.library.services.ICustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class CustomerServiceImplTest {

    @Autowired
    ICustomerService customerService;

    @Autowired
    ICustomerDAO customerDAO;

    @Test
    public void addNewCustomerCorrectTest() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Karol");
        customer.setSurname("Baran");
        customer.setPeselNumber("12345678901");
        Mockito.when(this.customerDAO.getCustomerByPeselNumber(customer.getPeselNumber())).thenReturn(null);
        Mockito.when(this.customerDAO.addNewCustomer(customer)).thenReturn(true);

        boolean result = this.customerService.addNewCustomer(customer);

        Assert.assertTrue(result);
    }

    @Test
    public void addNewCustomerIncorrectTest() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Janusz");
        customer.setSurname("Kowalski");
        customer.setPeselNumber("12312332142");
        Mockito.when(this.customerDAO.getCustomerByPeselNumber(customer.getPeselNumber())).thenReturn(new Customer());

        boolean result = this.customerService.addNewCustomer(customer);

        Assert.assertFalse(result);
    }
}
