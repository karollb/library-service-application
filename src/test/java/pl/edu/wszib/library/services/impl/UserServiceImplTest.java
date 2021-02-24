package pl.edu.wszib.library.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.library.configuration.TestConfiguration;
import pl.edu.wszib.library.dao.IBookDAO;
import pl.edu.wszib.library.dao.ICustomerDAO;
import pl.edu.wszib.library.dao.ILoanDAO;
import pl.edu.wszib.library.dao.IUserDAO;
import pl.edu.wszib.library.model.User;
import pl.edu.wszib.library.model.view.RegistrationModel;
import pl.edu.wszib.library.services.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    ICustomerDAO customerDAO;

    @MockBean
    ILoanDAO loanDAO;

    @Test
    public void addNewUserTest() {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("karol");
        registrationModel.setPass("karol111");
        registrationModel.setPass2("karol111");
        Mockito.when(this.userDAO.getUserByLogin("karol")).thenReturn(null);
        Mockito.when(this.userDAO.addNewUser(ArgumentMatchers.any())).thenReturn(true);

        boolean result = userService.addNewUser(registrationModel);

        Assert.assertTrue(result);
    }

    @Test
    public void addNewUserIncorrectTest() {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("piotrek");
        registrationModel.setPass("piotrek000");
        registrationModel.setPass2("piotrek000");
        Mockito.when(this.userDAO.getUserByLogin("piotrek")).thenReturn(new User());

        boolean result = userService.addNewUser(registrationModel);

        Assert.assertFalse(result);
    }
}
