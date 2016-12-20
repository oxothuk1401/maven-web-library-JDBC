package by.htp.library.service.exception;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -1444785856816637126L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
