package by.htp.library.listener;

import by.htp.library.dao.pool.ConnectionPool;
import by.htp.library.dao.exception.ConnectionPoolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	private static Logger logger = LogManager.getLogger(ConnectionPool.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		connectionPool.dispose();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			logger.fatal(e);
			throw new RuntimeException("No connection", e);
		}

	}
}
