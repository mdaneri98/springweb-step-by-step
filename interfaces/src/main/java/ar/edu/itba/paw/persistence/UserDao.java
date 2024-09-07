package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findById(long id);
    Optional<User> findByUsername(String username);
    User create(String username, String password);

}
