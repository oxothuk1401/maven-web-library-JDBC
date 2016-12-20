package by.htp.library.dao.exception;

public class ConnectionPoolException extends Exception {
	private static final long serialVersionUID = 2117866439873384254L;

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}
}