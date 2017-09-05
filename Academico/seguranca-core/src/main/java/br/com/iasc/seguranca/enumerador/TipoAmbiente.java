package br.com.iasc.seguranca.enumerador;

public enum TipoAmbiente {

	CLONE("Clone"),
	DESENVOLVIEMNTO("Desenvolvimento"),
	HOMOLOGACAO("Homologação"),
	PRODUCAO("Produção");

	private String descricao;

	private TipoAmbiente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
