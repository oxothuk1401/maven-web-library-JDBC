package by.htp.library.listners;

import by.htp.library.dao.connectionpool.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listners implements ServletContextListener {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		connectionPool.dispose();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}

	}
}
