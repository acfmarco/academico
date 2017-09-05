package br.com.iasc.seguranca.enumerador;

import br.com.iasc.seguranca.Entidades.BaseModel;

/**
 * Tipo de Usuário do Sistema BBPrevweb.
 * 
 * @author Tiago Menezes
 * @since 14/11/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public enum TipoLogin implements BaseModel {

	ALUNO(1, "Aluno"),
	FUNCIONARIO(2, "Funcionário"),
	PROFESSOR(3, "Professor"),
	EXTERNO(4, "Externo");

	private Integer codigo;
	private String descricao;

	private TipoLogin(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoLogin getTipoUsuario(Integer codigo) {
		for (TipoLogin tipo : values()) {
			if (tipo.getCodigo().equals(codigo)) {
				return tipo;
			}
		}
		return null;
	}

	public static TipoLogin getTipoUsuario(String codigo) {
		try {
			return getTipoUsuario(Integer.parseInt(codigo));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 *  
	 * @return boolean
	 */
	public boolean isProfessor() {
		return this.equals(TipoLogin.PROFESSOR);
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isAluno() {
		return this.equals(TipoLogin.ALUNO);
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isExterno() {
		return this.equals(TipoLogin.EXTERNO);
	}
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isFuncionario() {
		return this.equals(TipoLogin.FUNCIONARIO);
	}

}
