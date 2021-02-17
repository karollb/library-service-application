package pl.edu.wszib.library.dao;

import pl.edu.wszib.library.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
    boolean addNewUser(User user);

}
