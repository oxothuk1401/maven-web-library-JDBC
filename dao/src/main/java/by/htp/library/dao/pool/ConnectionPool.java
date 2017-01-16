package by.htp.library.dao.pool;

import by.htp.library.dao.exception.ConnectionPoolException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.sql.*;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public final class ConnectionPool {
	private static Logger logger = LogManager.getLogger(ConnectionPool.class);
	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;
	private final static ConnectionPool INSTANCE = new ConnectionPool();

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolsize;

	public static ConnectionPool getInstance() {
		return INSTANCE;
	}

	private ConnectionPool() {
		DBResourceManager dbResourceManager = DBResourceManager.getInstance();
		try {
			this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
			this.url = dbResourceManager.getValue(DBParameter.DB_URl);
			this.user = dbResourceManager.getValue(DBParameter.DB_USER);
			this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
			try {
				this.poolsize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOLSIZE));
			} catch (NumberFormatException e) {
				poolsize = 5;
			}
		}catch (MissingResourceException e){
			logger.fatal(e);
			throw new RuntimeException("No access to the database, check the connection settings", e);
		}
	}

	public void initPoolData() throws ConnectionPoolException {
		try {
			Class.forName(driverName);
			givenAwayConQueue = new ArrayBlockingQueue<>(poolsize);
			connectionQueue = new ArrayBlockingQueue<>(poolsize);
			for (int i = 0; i < poolsize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				PooledConnection pooledConnection = new PooledConnection(connection);
					connectionQueue.add(pooledConnection);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new ConnectionPoolException("SQLException in itinPoolData()", e);
		} catch (ClassNotFoundException e) {
			logger.error(e);
			throw new ConnectionPoolException("Can't finde driver class", e);
		}

	}

	public void dispose() {
		clearConnectionQueue();
	}

	private void clearConnectionQueue() {
		try {
			closeConnectionsQueue(givenAwayConQueue);
			closeConnectionsQueue(connectionQueue);
		} catch (SQLException e) {
			logger.error("Error closing the connection" , e);
		}
	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connectiong to the datasource", e);
		}
		return connection;
	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}

	private class PooledConnection implements Connection {
		private Connection connection;

		public PooledConnection(Connection connection) throws SQLException {
			this.connection = connection;
			this.connection.setAutoCommit(true);
		}

		public void reallyClose() throws SQLException {
			connection.close();
		}

		@Override
		public boolean isWrapperFor(Class<?> arg0) throws SQLException {
			return connection.isWrapperFor(arg0);
		}

		@Override
		public <T> T unwrap(Class<T> arg0) throws SQLException {
			return connection.unwrap(arg0);
		}

		@Override
		public void abort(Executor arg0) throws SQLException {
			connection.abort(arg0);

		}

		@Override
		public void clearWarnings() throws SQLException {
			connection.clearWarnings();

		}

		@Override
		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException("Attempt to close closed connection.");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!givenAwayConQueue.remove(this)) {
				throw new SQLException("Error deleting from givenAwayConPool");
			}
			if (!connectionQueue.offer(this)) {
				throw new SQLException("Error allocating connection in the pool");
			}

		}

		@Override
		public void commit() throws SQLException {
			connection.commit();

		}

		@Override
		public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
			return connection.createArrayOf(arg0, arg1);
		}

		@Override
		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		@Override
		public Clob createClob() throws SQLException {
			return connection.createClob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			return connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			return connection.createSQLXML();
		}

		@Override
		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		@Override
		public Statement createStatement(int arg0, int arg1) throws SQLException {
			return connection.createStatement(arg0, arg1);
		}

		@Override
		public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
			return connection.createStatement(arg0, arg1, arg2);
		}

		@Override
		public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
			return connection.createStruct(arg0, arg1);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		@Override
		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			return connection.getClientInfo();
		}

		@Override
		public String getClientInfo(String arg0) throws SQLException {
			return connection.getClientInfo(arg0);
		}

		@Override
		public int getHoldability() throws SQLException {
			return connection.getHoldability();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			return connection.getNetworkTimeout();
		}

		@Override
		public String getSchema() throws SQLException {
			return connection.getSchema();
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			return connection.getTypeMap();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		@Override
		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		@Override
		public boolean isValid(int arg0) throws SQLException {
			return connection.isValid(arg0);
		}

		@Override
		public String nativeSQL(String arg0) throws SQLException {
			return connection.nativeSQL(arg0);
		}

		@Override
		public CallableStatement prepareCall(String arg0) throws SQLException {
			return connection.prepareCall(arg0);
		}

		@Override
		public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
			return connection.prepareCall(arg0, arg1, arg2);
		}

		@Override
		public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
			return connection.prepareCall(arg0, arg1, arg2, arg3);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0) throws SQLException {
			return connection.prepareStatement(arg0);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
			return connection.prepareStatement(arg0, arg1);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
			return connection.prepareStatement(arg0, arg1);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
			return connection.prepareStatement(arg0, arg1);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
			return connection.prepareStatement(arg0, arg1, arg2);
		}

		@Override
		public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
			return connection.prepareStatement(arg0, arg1, arg2, arg3);
		}

		@Override
		public void releaseSavepoint(Savepoint arg0) throws SQLException {
			connection.releaseSavepoint(arg0);
		}

		@Override
		public void rollback() throws SQLException {
			connection.rollback();
		}

		@Override
		public void rollback(Savepoint arg0) throws SQLException {
			connection.rollback(arg0);
		}

		@Override
		public void setAutoCommit(boolean arg0) throws SQLException {
			connection.setAutoCommit(arg0);
		}

		@Override
		public void setCatalog(String arg0) throws SQLException {
			connection.setCatalog(arg0);
		}

		@Override
		public void setClientInfo(Properties arg0) throws SQLClientInfoException {
			connection.setClientInfo(arg0);
		}

		@Override
		public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
			connection.setClientInfo(arg0, arg1);
		}

		@Override
		public void setHoldability(int arg0) throws SQLException {
			connection.setHoldability(arg0);
		}

		@Override
		public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
			connection.setNetworkTimeout(arg0, arg1);
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			return connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			return connection.setSavepoint(name);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);
		}
	}
}
