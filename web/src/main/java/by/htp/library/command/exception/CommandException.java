package by.htp.library.command.exception;

public class CommandException extends Exception {
	private static final long serialVersionUID = 976473610658412960L;

	public CommandException() {
		super();
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}
}
