package by.htp.library.dao;

import java.io.File;

import by.htp.library.dao.pool.ConnectionPool;
import org.junit.Assert;
import org.junit.Test;

public class JUnitTest {
	private final static String DB_PROPERTIES_PATH = "src/main/resources/db.properties";

	@Test
	public void dataBasePropertiesExists() {
		File file = new File(DB_PROPERTIES_PATH);
		Assert.assertTrue(file.exists());
	}

	@Test
	public void createConnectionPool() {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		Assert.assertNotNull(connectionPool);
	}

}
