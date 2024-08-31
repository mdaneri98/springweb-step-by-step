package ar.edu.itba.paw.services;

import ar.edu.itba.paw.User;
import ar.edu.itba.paw.persistence.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;


public class UserServiceImplTest {

    private static final String USERNAME = "username";
    private UserServiceImpl userService;

    private UserDao mock;

    @Before
    public void setUp() {
        mock = Mockito.mock(UserDao.class);
        this.userService = new UserServiceImpl(mock);
    }

    @Test
    public void testCreate() {
        // 1. Precondiciones.
        Mockito.when(mock.create(eq(USERNAME))).thenReturn(new User(1, USERNAME));

        // 2. Ejercito la clase under test.
        User user = userService.create(USERNAME);

        // 3. Valido las postcondiciones
        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());

    }

    @Test(expected = DuplicateKeyException.class)
    public void testCreateDuplicate() {
        // 1. Precondiciones.
        Mockito.when(mock.create(eq(USERNAME))).thenThrow(DuplicateKeyException.class);

        // 2. Ejercito la clase under test.
        User user = userService.create(USERNAME);

        // 3. Valido las postcondiciones
        fail();
    }

}
