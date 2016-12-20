package by.htp.library.dao.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = -8158355570810563089L;

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
}
