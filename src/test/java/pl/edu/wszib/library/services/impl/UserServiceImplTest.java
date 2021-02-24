package pl.edu.wszib.library.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.library.configuration.TestConfiguration;
import pl.edu.wszib.library.model.view.RegistrationModel;
import pl.edu.wszib.library.services.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @Test
    public void addNewUserTest() {
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setLogin("karol");
        registrationModel.setPass("karol111");
        registrationModel.setPass2("karol111");

        boolean result = userService.addNewUser(registrationModel);

        Assert.assertTrue(result);
    }
}
