package br.com.iasc.infra.repository.dto;

/**
 *
 * @author Tiago Menezes
 * @since 20/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public class ObjetoSql {

	private Long codigo;
	private String nomeCampo;
	private String atalho;

	public ObjetoSql() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getAtalho() {
		return atalho;
	}

	public void setAtalho(String atalho) {
		this.atalho = atalho;
	}
}
