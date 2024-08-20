package ar.edu.itba.paw.services;

import ar.edu.itba.paw.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {


    @Override
    public Optional<User> findById(long id) {
        return Optional.of(new User("Yo mismo :)"));
    }

}
