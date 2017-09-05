package br.com.iasc.utils.exception;

import javax.persistence.OptimisticLockException;

import br.com.iasc.utils.UtilJava;

/**
 * Exceção utilizada para encapsular um {@link OptimisticLockException}. Este ocorre quando
 * é utilizado a estratégia de lock otimista na entidade mapeada.
 *
 * @author Lourival Júnior
 * @since 18/04/2013
 * 
 * Copyright notice (c) 2013 BBPrevidência S/A 
 */
public class AcessoConcorrenteException extends RuntimeException {

	private static final long serialVersionUID = 7973122870875565998L;
	private String mensagem;

	public AcessoConcorrenteException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}

	public AcessoConcorrenteException(Throwable cause) {
		super(cause);
		if (!UtilJava.isStringVazia(cause.getMessage())) {
			this.mensagem = cause.getMessage();
		}
	}

	public AcessoConcorrenteException(String message, Throwable cause) {
		super(message, cause);
		this.mensagem = message;
	}

	@Override
	public String getMessage() {
		return this.mensagem;
	}

}
