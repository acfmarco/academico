package br.com.iasc.utils.exception;

public class AcademicoException extends RuntimeException {

	private static final long serialVersionUID = 9104109396993245062L;

	private String codigo;

	public AcademicoException() {
		super();
	}

	public AcademicoException(String message) {
		super(message);
		this.codigo = message;
	}

	public AcademicoException(String codigo, String message) {
		super(message);
		this.codigo = codigo;
	}

	public AcademicoException(String codigo, String message, Throwable cause) {
		super(message, cause);
	}

	public AcademicoException(String message, Throwable cause) {
		super(message, cause);
	}

	public AcademicoException(Throwable cause) {
		super(cause);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
