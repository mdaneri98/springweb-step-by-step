package ar.edu.itba.paw.services;

import ar.edu.itba.paw.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(long id);
    User create(String username);

}
