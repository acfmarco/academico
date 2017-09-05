package br.com.iasc.utils.mascara;

import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.exception.InfraException;
import br.com.iasc.utils.formatacao.UtilFormatacao;

/**
 * Enum utilizado para recuperar as informações do arquivo usando uma mascara específica.
 *
 * @author Marcos Célio
 * @since 11/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A
 */
public enum TipoMascara {

	SEM_MASCARA("", ""),
	CPF("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1.$2.$3-$4"),
	CNPJ("##.###.###/####-##", "criar"),
	TELEFONE_SEMDDD("([0-9]{4})([0-9]{4})", "$1-$2"),
	MONETARIO("([0-9]{5})([0-9]{3})", "$1.$2"),
	CEP("([0-9]{5})([0-9]{3})", "$1-$2"),
	DATA_DIA_MES_ANO("([0-9]{4})([0-9]{2})([0-9]{2})", "$3/$2/$1");

	private String regex;
	private String mascara;

	private TipoMascara(String regex, String mascara) {
		this.regex = regex;
		this.mascara = mascara;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	/**
	 * Aplica a máscara devidamente contida em mascara a campoMascara.
	 * 
	 * @param mascara que deve ser aplica a campoMascara
	 * @param campoMascara que deve ter mascara aplicada.
	 * @return campoMascara com mascara devidamente aplicada.
	 * @throws InfraException 
	 */
	public static String aplicaMascara(TipoMascara mascara, String campoMascara) throws InfraException {
		if (campoMascara == null || UtilJava.isStringVazia(campoMascara, true)) {
			return "";
		} else {
			if (mascara.equals(TipoMascara.SEM_MASCARA)) {
				return campoMascara;
			} else {
				return mascara.aplicarMascara(campoMascara);
			}
		}
	}

	@SuppressWarnings("incomplete-switch")
	public String aplicarMascara(String valorFormatar) throws InfraException {
		String formatada = null;

		switch (this) {

		case CPF:
			formatada = UtilFormatacao.formatarCPF(valorFormatar);
			break;
		case TELEFONE_SEMDDD:
			formatada = UtilFormatacao.formatarTelefoneSemDDD(valorFormatar);
			break;
		case CEP:
			formatada = UtilFormatacao.formatarCep(valorFormatar);
			break;
		case DATA_DIA_MES_ANO:
			formatada = UtilFormatacao.formatarDataDiaMesAno(valorFormatar);
			break;
		case MONETARIO:
			formatada = UtilFormatacao.formatarValorMonetario(valorFormatar);
			break;
		}

		return formatada;
	}

}
