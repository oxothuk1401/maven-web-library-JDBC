package by.htp.library.listener;

import by.htp.library.dao.pool.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {
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
