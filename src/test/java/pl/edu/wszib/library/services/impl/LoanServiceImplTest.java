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
import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.dao.ILoanDAO;
import pl.edu.wszib.library.model.Book;
import pl.edu.wszib.library.model.Customer;
import pl.edu.wszib.library.model.Loan;
import pl.edu.wszib.library.services.ILoanService;
import pl.edu.wszib.library.session.SessionObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class LoanServiceImplTest {

    @Autowired
    ILoanService loanService;

    @Autowired
    ILoanDAO loanDAO;

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    ICustomerDAO customerDAO;



    @Test
    public void getCustomersWithLoansCorrectTest() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("Karol");
        customer.setSurname("Baran");
        customer.setPeselNumber("12345678912");
        Mockito.when(this.loanDAO.getAllLoans()).thenReturn(getLoanList());

        List<Customer> result = this.loanService.getCustomersWithLoans();

        Assert.assertEquals(customer.getId(), result.get(0).getId());
        Assert.assertEquals(customer.getFirstName(), result.get(0).getFirstName());
        Assert.assertEquals(customer.getSurname(), result.get(0).getSurname());
        Assert.assertEquals(customer.getPeselNumber(), result.get(0).getPeselNumber());

    }

    @Test
    public void getCustomersWithLoansTest() {
        List<Loan> loans = new ArrayList<>();
        Mockito.when(this.loanDAO.getAllLoans()).thenReturn(loans);

        List<Customer> result = this.loanService.getCustomersWithLoans();

        Assert.assertTrue(result.isEmpty());

    }


    private List<Loan> getLoanList() {
        List<Loan> loans = new ArrayList<>();

        Customer customer = new Customer(1, "Karol", "Baran", "12345678912");
        Loan loan = new Loan();

        loan.setId(1);
        loan.setCustomer(customer);
        loan.setBook(new Book());
        loan.setDate(new Date());

        loans.add(loan);

        return loans;
    }

}
