package pl.edu.wszib.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.library.dao.IUserDAO;
import pl.edu.wszib.library.model.User;
import pl.edu.wszib.library.model.view.RegistrationModel;
import pl.edu.wszib.library.services.IUserService;
import pl.edu.wszib.library.session.SessionObject;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void authenticate(User user) {
        User userFromDataBase = this.userDAO.getUserByLogin(user.getLogin());
        if (userFromDataBase == null) {
            return;
        }

        if (user.getPass().equals(userFromDataBase.getPass())) {
            this.sessionObject.setLoggedUser(userFromDataBase);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);

    }

    @Override
    public boolean addNewUser(RegistrationModel registrationModel) {
        if (this.userDAO.getUserByLogin(registrationModel.getLogin()) != null) {
            return false;
        }

        User newUser = new User(0, registrationModel.getLogin(), registrationModel.getPass(), User.Role.USER);

        return this.userDAO.addNewUser(newUser);
    }
}
