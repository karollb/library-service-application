package pl.edu.wszib.library.services;

import pl.edu.wszib.library.model.User;
import pl.edu.wszib.library.model.view.RegistrationModel;

public interface IUserService {
    void authenticate(User user);

    void logout();

    boolean addNewUser(RegistrationModel registrationModel);

    boolean addNewAdmin(RegistrationModel registrationModel);
}
