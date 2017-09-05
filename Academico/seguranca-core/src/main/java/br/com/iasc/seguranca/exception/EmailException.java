package br.com.iasc.seguranca.exception;

public class EmailException extends Throwable {

	private static final long serialVersionUID = -1676558619616043949L;
	private String message;

	public EmailException() {
		super();
	}

	public EmailException(String message) {
		super(message);
		this.message = message;
	}

	public EmailException(Throwable cause) {
		super(cause);
	}

	public EmailException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
