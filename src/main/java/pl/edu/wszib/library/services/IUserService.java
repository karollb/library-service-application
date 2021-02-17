package pl.edu.wszib.library.services;

import pl.edu.wszib.library.model.User;

public interface IUserService {
    void authenticate(User user);

    void logout();
}
