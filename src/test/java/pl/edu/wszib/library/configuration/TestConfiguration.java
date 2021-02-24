package pl.edu.wszib.library.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.dao.ILoanDAO;
import pl.edu.wszib.library.dao.IUserDAO;
import pl.edu.wszib.library.dao.impl.BookDAOStub;
import pl.edu.wszib.library.dao.impl.CustomerDAOStub;
import pl.edu.wszib.library.dao.impl.LoanDAOStub;
import pl.edu.wszib.library.dao.impl.UserDAOStub;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.library.controllers",
        "pl.edu.wszib.library.services.impl",
        "pl.edu.wszib.library.session"
})
public class TestConfiguration {

    /*@Bean
    public IBookDAO bookDAO() {
        return Mockito.mock(IBookDAO.class);
    }

    @Bean
    public IUserDAO userDAO() {
        return Mockito.mock(IUserDAO.class);
    }

    @Bean
    public ICustomerDAO customerDAO() {
        return Mockito.mock(ICustomerDAO.class);
    }

    @Bean
    public ILoanDAO loanDAO() {
        return Mockito.mock(ILoanDAO.class);
    }*/
}
