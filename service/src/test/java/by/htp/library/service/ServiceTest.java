package by.htp.library.service;

import by.htp.library.dao.pool.ConnectionPool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by oxothuk1401 on 03.10.2016.
 */
public class ServiceTest {

    ConnectionPool connectionPool = null;
    @Before
    public void setUp() throws Exception {
        connectionPool = ConnectionPool.getInstance();
        connectionPool.initPoolData();}

    @Test
    public void showUsersNotNull() throws Exception {
        assertNotNull(UserService.getInstance().matchExistLogin(""));
    }


}
