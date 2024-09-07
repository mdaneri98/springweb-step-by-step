package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.User;
import ar.edu.itba.paw.persistence.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;


@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Sql(scripts = "classpath:users.sql")
public class UserJdbcDaoTest {

    private static final long PREEXISTING_USER = 500;
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Autowired
    private UserJdbcDao userJdbcDao;

    @Autowired
    private DataSource ds;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
    }
    
    @Test
    public void testCreate() {
        // 1. Precondiciones
        // Todos los tests arrancan con la tabla de users vacía
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users");

        
        // 2. Ejercito la clase under test
        User user = userJdbcDao.create(USERNAME, PASSWORD);
        
        // 3. Postcondiciones 
        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "users", String.format("username = '%s'", USERNAME)));
    }

    @Test
    public void testFindById() {
        // 1. Precondiciones: Debe existir un usuario
        /* Estaría mal realizarlo de la siguiente manera ya que deja de ser un test unitario
           Si falla el test, no se si se debe al `create` o al `findById`.
                User user = userJdbcDao.create(USERNAME);
         */

        // 2. Ejercito la clase under test
        Optional<User> maybeUser = userJdbcDao.findById(PREEXISTING_USER);

        // 3. Postcondiciones
        assertTrue(maybeUser.isPresent());
        assertEquals(PREEXISTING_USER, maybeUser.get().getId());
    }
    
    
}
