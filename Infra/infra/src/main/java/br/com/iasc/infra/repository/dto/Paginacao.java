package br.com.iasc.infra.repository.dto;

import java.io.Serializable;

/**
 * Classe responsável por receber os limites de páginação de dados.
 *
 * @author Tiago Menezes
 * @since 19/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public class Paginacao implements Serializable {

	private static final long serialVersionUID = -7801630864859730842L;

	private Integer posicao;
	private Integer limite;

	public Paginacao() {
		super();
	}

	public Paginacao(Integer posicao, Integer limite) {
		this.posicao = posicao;
		this.limite = limite;
	}

	public Integer getLimite() {
		return limite;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setLimite(Integer limite) {
		this.limite = limite;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
}