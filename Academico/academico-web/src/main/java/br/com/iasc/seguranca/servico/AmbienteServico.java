package br.com.iasc.seguranca.servico;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.iasc.seguranca.enumerador.TipoAmbiente;

@Component
public class AmbienteServico {

	private static String AMB_DESENV = "DESENV";

	@Value("#{propriedades['sistema.ambiente']}")
	private String ambiente;

	@Value("#{propriedades['url.seguranca']}")
	private String seguranca;
	
	@Value("#{propriedades['url.academico']}")
	private String academico;
	
	@Value("#{propriedades['url.tesouraria']}")
	private String tesouraria;

	public boolean isDesenvolvimento() {
		return AMB_DESENV.equalsIgnoreCase(getAmbiente());
	}

	public TipoAmbiente getTipoAmbiente() {
		if (StringUtils.isNotBlank(getAmbiente())) {
			for (TipoAmbiente tipo : TipoAmbiente.values()) {
				if (tipo.getDescricao().equalsIgnoreCase(getAmbiente())) {
					return tipo;
				}
			}
		}
		return null;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}
		
	public String getSeguranca() {
		return seguranca;
	}

	public void setSeguranca(String seguranca) {
		this.seguranca = seguranca;
	}

	public static String getAmbDesenv() {
		return AMB_DESENV;
	}

	public static void setAmbDesenv(String ambDesenv) {
		AMB_DESENV = ambDesenv;
	}

	public String getAcademico() {
		return academico;
	}

	public void setAcademico(String academico) {
		this.academico = academico;
	}

	public String getTesouraria() {
		return tesouraria;
	}

	public void setTesouraria(String tesouraria) {
		this.tesouraria = tesouraria;
	}
	
}
