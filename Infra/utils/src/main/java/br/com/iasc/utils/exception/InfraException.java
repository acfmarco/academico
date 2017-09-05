package br.com.iasc.utils.exception;

import br.com.iasc.utils.UtilJava;

/**
 *
 * @author Tiago Menezes
 * @since 20/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public class InfraException extends Throwable {

	private static final long serialVersionUID = 2595285571660288994L;

	private String mensagem;

	/**
	 * @param message
	 */
	public InfraException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	/**
	 * @param cause
	 */
	public InfraException(Throwable cause) {
		super(cause);
		if (!UtilJava.isStringVazia(cause.getMessage())) {
			this.mensagem = cause.getMessage();
		}
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InfraException(String message, Throwable cause) {
		super(message, cause);
		this.mensagem = message;
	}

	@Override
	public String getMessage() {
		return this.mensagem;
	}

}
