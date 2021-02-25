package pl.edu.wszib.library.dao.impl;

import pl.edu.wszib.library.dao.IUserDAO;
import pl.edu.wszib.library.model.User;

public class UserDAOStub implements IUserDAO {
    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public boolean addNewUser(User user) {
        return false;
    }
}
