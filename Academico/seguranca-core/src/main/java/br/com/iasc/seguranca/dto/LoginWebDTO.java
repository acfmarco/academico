package br.com.iasc.seguranca.dto;

import java.io.Serializable;

import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.enumerador.TipoLogin;

/**
 * Classe que contém todas as informações de sessão do usuário logado na aplicação;
 * 
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A
 * 
 */
public class LoginWebDTO implements Serializable {

	private static final long serialVersionUID = 5106157500815600974L;

	private TipoLogin tipoLogin;
	private Usuario usuarioSessao;

	public LoginWebDTO() {
		super();
	}

	/*
	public boolean usuarioPossuiFuncionalidadeAcessoTotal(String literal) {

		if (this.usuarioSessao != null) {
			for (br.bb.previdencia.seguranca.dto.Funcionalidade funcionalidade : this.usuarioSessao.getListaFuncionalidade()) {
				if (funcionalidade.getNome().equalsIgnoreCase(literal)) {

					if (funcionalidade.getTipoAcesso().equals("T")) {
						return true;
					}

				}
			}

			logger.warn("O usuário " + this.usuarioSessao.getDescricaoLogin() + " não possui permissão de acesso para a funcionalidade com literal " + literal);
		}

		return false;
	}
	 */

	/**
	 * Verifica se o usuario possui alguma funcionalidade da lista passada na String. As funcionalidades devem vir separadas por vírgula.
	 * 
	 * @param literais
	 * @return
	 *//*
	public boolean possuiAlgumaFuncionalidade(String literais) {
		if (StringUtils.isEmpty(literais)) {
			throw new IllegalArgumentException("Valor inválido para a busca de funcionalidades!");
		}

		String[] funcionalidades = literais.split(",");

		for (String funcionalidade : funcionalidades) {
			if (this.usuarioPossuiFuncionalidade(funcionalidade)) {
				return true;
			}
		}

		return false;
	}
*/
	public boolean isAluno() {
		return this.tipoLogin == TipoLogin.ALUNO;
	}

	public boolean isExterno() {
		return this.tipoLogin == TipoLogin.EXTERNO;
	}
	
	public boolean isFuncionario() {
		return this.tipoLogin == TipoLogin.FUNCIONARIO;
	}

	public boolean isProfessor(){
		return this.tipoLogin == TipoLogin.PROFESSOR;
	}

	public TipoLogin getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(TipoLogin tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

	public Usuario getUsuarioSessao() {
		return usuarioSessao;
	}

	public void setUsuarioSessao(Usuario usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}
	
}