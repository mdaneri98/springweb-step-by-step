package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.User;
import ar.edu.itba.paw.persistence.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UserJdbcDaoTest {

    private static final String USERNAME = "username";

    @Autowired
    private UserJdbcDao userJdbcDao;
    
    @Test
    public void testCreate() {
        // 1. Precondiciones

        
        // 2. Ejercito la clase under test
        User user = userJdbcDao.create(USERNAME);
        
        // 3. Postcondiciones 
        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
    }
    
    
}
