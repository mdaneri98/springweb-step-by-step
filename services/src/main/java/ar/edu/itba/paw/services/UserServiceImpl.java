package ar.edu.itba.paw.services;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.persistence.UserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(final UserDao userDao, final PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User create(String username, String password) {
        /*
        TODO:
         1. Validar inputs
         2. Ingresarlo en la base de datos
         3. Generar token de validació y guardarlo en la base.
         4. Enviar token de val. en un correo de bienvenida
         5. Agregar al usuario a una cola de verificación manual
         6. ...
         */
        return userDao.create(username, passwordEncoder.encode(password));
    }




}
