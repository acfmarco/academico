package br.com.iasc.seguranca.exception;

public class RegistroNaoEncontradoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5978736075343039674L;
	String mensagem;
	Exception t;

	public RegistroNaoEncontradoException() {
		super();

	}

	public RegistroNaoEncontradoException(Exception e) {
		super(e);
		this.t = e;
	}

	public RegistroNaoEncontradoException(String mensagem) {
		super(mensagem);
		this.mensagem = mensagem;
	}
}