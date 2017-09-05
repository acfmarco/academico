package br.com.iasc.utils.formatacao;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.exception.InfraException;

/**
 *
 * @author Tiago Menezes
 * @since 20/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public class FormatarDados {

	/**
	 * Metodo responsevel pela retirada de carateres especiais
	 * 
	 * @param texto
	 * @return uma string sem os caracteres especiais
	 */

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public static String retirarCaracteresEspeciais(String texto) {

		StringBuffer textoLido = new StringBuffer();
		Vector caracteres = new Vector();

		if (texto != null && !texto.equalsIgnoreCase("")) {

			for (int i = 0; i < texto.length(); i++) {

				caracteres.add(i, texto.charAt(i));
			}

			for (int i = 0; i < caracteres.size(); i++) {

				if (caracteres.get(i).toString().equalsIgnoreCase("/") || caracteres.get(i).toString().equalsIgnoreCase("<") || caracteres.get(i).toString().equalsIgnoreCase(">")
						|| caracteres.get(i).toString().equalsIgnoreCase("=") || caracteres.get(i).toString().equalsIgnoreCase("#") || caracteres.get(i).toString().equalsIgnoreCase("&")
						|| caracteres.get(i).toString().equalsIgnoreCase("(") || caracteres.get(i).toString().equalsIgnoreCase(")") || caracteres.get(i).toString().equalsIgnoreCase("+")
						|| caracteres.get(i).toString().equalsIgnoreCase("-") || caracteres.get(i).toString().equalsIgnoreCase("$") || caracteres.get(i).toString().equalsIgnoreCase("@")
						|| caracteres.get(i).toString().equalsIgnoreCase(";") || caracteres.get(i).toString().equalsIgnoreCase("_") || caracteres.get(i).toString().equalsIgnoreCase("'")
						|| caracteres.get(i).toString().equalsIgnoreCase(".") || caracteres.get(i).toString().equalsIgnoreCase("(") || caracteres.get(i).toString().equalsIgnoreCase(")")
						|| caracteres.get(i).toString().equalsIgnoreCase(" ")) {

					caracteres.set(i, "");
				}

				textoLido.append(caracteres.get(i));
			}
		}

		return textoLido.toString().trim();
	}

	/**
	 * Metodo responsavel por retirar a acentuacao grafica
	 * 
	 * @param texto
	 * @return {@link String}
	 */

	public static String retirarAcentuacaoGrafica(String texto) {

		texto = texto.replaceAll("[ÂÀÁÄÃ]", "A");
		texto = texto.replaceAll("[âãàáä]", "a");
		texto = texto.replaceAll("[ÊÈÉË]", "E");
		texto = texto.replaceAll("[êèéë]", "e");
		texto = texto.replaceAll("ÎÍÌÏ", "I");
		texto = texto.replaceAll("îíìï", "i");
		texto = texto.replaceAll("[ÔÕÒÓÖ]", "O");
		texto = texto.replaceAll("[ôõòóö]", "o");
		texto = texto.replaceAll("[ÛÙÚÜ]", "U");
		texto = texto.replaceAll("[ûúùü]", "u");
		texto = texto.replaceAll("Ç", "C");
		texto = texto.replaceAll("ç", "c");
		texto = texto.replaceAll("[ýÿ]", "y");
		texto = texto.replaceAll("Ý", "Y");
		texto = texto.replaceAll("ñ", "n");
		texto = texto.replaceAll("Ñ", "N");
		texto = texto.replaceAll("['<>\\|/]", "");
		return texto;
	}

	/**
	 * Metodo responsevel pela retirada de carateres especiais
	 * 
	 * @param texto
	 * @return uma string sem os caracteres especiais
	 */

	@SuppressWarnings( { "unchecked", "rawtypes" })
	public static boolean verificarCaracteresEspeciais(String texto) {

		Vector caracteres = new Vector();

		for (int i = 0; i < texto.length(); i++) {

			caracteres.add(i, texto.charAt(i));
		}

		for (int i = 0; i < caracteres.size(); i++) {

			if (caracteres.get(i).toString().equalsIgnoreCase("/") || caracteres.get(i).toString().equalsIgnoreCase("<") || caracteres.get(i).toString().equalsIgnoreCase(">")
					|| caracteres.get(i).toString().equalsIgnoreCase("=") || caracteres.get(i).toString().equalsIgnoreCase("#") || caracteres.get(i).toString().equalsIgnoreCase("&")
					|| caracteres.get(i).toString().equalsIgnoreCase("(") || caracteres.get(i).toString().equalsIgnoreCase(")") || caracteres.get(i).toString().equalsIgnoreCase("+")
					|| caracteres.get(i).toString().equalsIgnoreCase("-") || caracteres.get(i).toString().equalsIgnoreCase("$") || caracteres.get(i).toString().equalsIgnoreCase("@")
					|| caracteres.get(i).toString().equalsIgnoreCase(";") || caracteres.get(i).toString().equalsIgnoreCase("_") || caracteres.get(i).toString().equalsIgnoreCase("'")
					|| caracteres.get(i).toString().equalsIgnoreCase(".")) {

				return true;
			}

		}

		return false;
	}

	/**
	 * Metodo por verificar se a data e valida 
	 * 
	 * @param data
	 * @return boolean 
	 */

	public static boolean validarData(String data) {

		int dia;
		int mes;

		//DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		//String dataRecebida = df.format(data); 

		if (data.trim().length() == 10) {

			dia = Integer.parseInt(data.substring(0, 2));
			mes = Integer.parseInt(data.substring(3, 5));

			if ((mes == 1 || mes == 2 || mes == 3 || mes == 4 || mes == 5 || mes == 6 || mes == 7 || mes == 8 || mes == 9 || mes == 10 || mes == 11 || mes == 12) && (dia >= 1 && dia <= 31)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Metodo responsevel por converter String no formato yyyy-MM-dd hh:mm:ss.S
	 * no objeto java.util.Date
	 * 
	 * @param data
	 * @return Date
	 */

	public static Date converterStringDate(String data) {

		SimpleDateFormat dataFormato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		Date dataConvertida = null;

		if (data != null && !data.equalsIgnoreCase("")) {

			try {
				dataConvertida = dataFormato.parse(data);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return dataConvertida;
	}

	public static Date converterStringDate(String data, String formato) {

		Date dataConvertida = null;

		if (data != null && data.trim().length() >= 10 && formato != null && !formato.equalsIgnoreCase("")) {

			SimpleDateFormat dataFormato = new SimpleDateFormat(formato);

			try {
				dataConvertida = dataFormato.parse(data.substring(0, 10));

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return dataConvertida;
	}

	public static String formatarStatus(String status) {

		if (status.equalsIgnoreCase("I")) {
			return "INATIVO";
		} else if (status.equalsIgnoreCase("A")) {
			return "ATIVO";
		}

		return status;
	}

	public static String formatarStatusDiversos(String status) {

		if (status.equalsIgnoreCase("1")) {
			return "S";
		} else if (status.equalsIgnoreCase("2")) {
			return "N";
		}
		return status;
	}

	public static boolean compararData(Date primeiraData, Date segundaData) {

		if (primeiraData != null && segundaData != null) {

			if (primeiraData.before(segundaData)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	public static boolean compararDataAtual(Date data) {

		if (data != null) {

			Date dateAtual = new Date();

			SimpleDateFormat dataFormato = new SimpleDateFormat("yyyy/MM/dd");
			String dataAtualFormatada = dataFormato.format(dateAtual);
			String dataRecebida = dataFormato.format(data);
			Date dataRec = converterStringDate(dataRecebida, "yyyy/MM/dd");
			Date dataAtFormt = converterStringDate(dataAtualFormatada, "yyyy/MM/dd");

			if (dataRec.before(dataAtFormt)) {
				return true;
			} else {
				return false;
			}
		}

		return true;
	}

	public static String clobToString(Clob pClob) {

		String retorno = null;
		if (pClob != null) {
			try {
				retorno = pClob.getSubString(1, new Long(pClob.length()).intValue());
			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		return retorno;
	}

	/**
	 * Método responsável por verificar Parte da String
	 * 
	 * @param texto
	 * @param delimitador
	 * @return boolean
	 */

	public static boolean verficarParteString(String texto, String delimitador) {

		if (texto != null && !texto.equalsIgnoreCase("") && delimitador != null && !delimitador.equalsIgnoreCase("")) {

			int pos = texto.indexOf(delimitador);

			if (pos < 0) {
				return false;
			}
		}

		return true;
	}

	public static String tranformarDateString(Date date) {
		if (date == null)
			return "";
		else {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			return formatador.format(date);
		}
	}

	/**
	 * Metodo responsavel por converter um objeto do tipo string 'S' ou 'N' em boolean.
	 * 
	 * @param String
	 * @return {@link Boolean}
	 */
	public static boolean converterStringParaBoolean(String parametro) {

		if (parametro != null && !UtilJava.isStringVazia(parametro)) {
			if (parametro.equalsIgnoreCase("S")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Metodo responsavel por converter um objeto do tipo boolean para string 'S' ou 'N'. 
	 * 
	 * @param boolean
	 * @return {@link String}
	 */
	public static String converterBooleanParaString(boolean parametro) {

		if (parametro) {
			return "S";
		} else {
			return "N";
		}
	}

	/**
	 * Método responsável por converter o padrão de data
	 * 
	 * @param data
	 * @return {@link String}
	 */
	public static String converterPadraoData(Date data) {

		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		String result = out.format(data);

		return result;
	}

	/**
	 * Método responsável por calcular a data de aponsentadoria 
	 * 
	 * @param idade
	 * @return {@link Date}
	 */

	public static Integer calcularIdade(Date dataNascimento, Date dataAposentadoria) {

		Calendar calendarioDataNasc = Calendar.getInstance();
		Calendar calendarioApon = Calendar.getInstance();

		int ano = 0;

		calendarioDataNasc.setTime(dataNascimento);
		calendarioApon.setTime(dataAposentadoria);

		int anoNascimento = calendarioDataNasc.get(Calendar.YEAR);
		int anoAposentadoria = calendarioApon.get(Calendar.YEAR);

		ano = anoAposentadoria - anoNascimento;
		return ano;
	}

	/**
	 * Método responsável por calcular a data de aponsentadoria 
	 * 
	 * @param idade
	 * @return {@link Date}
	 */
	public static Date calcularDataAposentadoria(Date dataNascimento, int idade) {

		Date dataRetorno = new Date();
		Calendar calendarioDataNascimento = Calendar.getInstance();

		Long contador = 0l;

		calendarioDataNascimento.setTime(dataNascimento);

		while (contador < idade) {
			calendarioDataNascimento.add(Calendar.YEAR, +1);
			contador++;
		}

		dataRetorno = calendarioDataNascimento.getTime();

		return dataRetorno;
	}

	/**
	 * Método responsável por calcular o percentual de contribuição,
	 * de acordo com o valor informado pelo usuário
	 * 
	 * @param salario
	 * @param valorContribuicao
	 * @return {@link BigDecimal}
	 * @throws InfraException 
	 */
	public static BigDecimal calcularPercentualContribuicaoPorValor(BigDecimal salario, BigDecimal valorContribuicao) throws InfraException {

		try {

			BigDecimal valorTotalContribuicao = valorContribuicao.multiply(new BigDecimal(100));
			Double valorPercentual = valorTotalContribuicao.doubleValue() / salario.doubleValue();

			return new BigDecimal(valorPercentual);

		} catch (Exception e) {
			throw new InfraException(e);
		}
	}

	/**
	 * Método responsável por calcular o percentual de contribuição,
	 * de acordo com o valor informado pelo usuário
	 * 
	 * @param salario
	 * @param valorContribuicao
	 * @return {@link BigDecimal}
	 * @throws InfraException 
	 */
	public static BigDecimal calcularValorContribuicaoPorValor(BigDecimal salario, BigDecimal percentualContribuicao) throws InfraException {
		try {

			BigDecimal resultado = (salario.multiply(percentualContribuicao)).divide(new BigDecimal(100));

			return resultado;
		} catch (Exception e) {
			throw new InfraException("Não foi possível realizar está operação");
		}
	}

	/**
	 * Valida se o cep é válido ou não. 
	 * 
	 * @author andre.mello
	 * @param cep a ser validado.
	 * @returne true, se o cep é válido ou false, se inválido.
	 */
	public static Boolean isCEPValido(String cep) {

		Pattern pattern = Pattern.compile("[0-9]{5}-[0-9]{3}");

		Matcher m = pattern.matcher(cep);
		return m.matches();

	}

	/**
	 * Valida se o telefone é válido ou não.
	 * 
	 * @author andre.mello
	 * @param telefone a ser validado.
	 * @return true, se o telefone é válido ou false, se inválido.
	 */
	public static Boolean isTelefoneValido(String telefone) {

		Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{4}");

		Matcher m = pattern.matcher(telefone);
		return m.matches();

	}

	/**
	 * Valida se o email é válido ou não.
	 * 
	 * @author andre.mello
	 * @param email a ser validado.
	 * @return true, se o email é válido ou false, se inválido.
	 */
	public static Boolean isEmailValido(String email) {

		Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");

		Matcher m = pattern.matcher(email);
		return m.matches();

	}

	public static boolean isNumeroValido(String valor) {
		if (valor == null)
			return false;

		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(valor);

		//valido
		return matcher.matches();
	}

	//Para verificar digito de agencia pois pode ser X
	//Erro Ocorrido Demanda 29042 - Marco Figueiredo
	public static boolean isNumeroAgenciaValida(String valor) {
		if (valor == null)
			return false;

		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher matcher = pattern.matcher(valor);

		Pattern patternLetraX = Pattern.compile("[X]+");
		Matcher matcherLetraX = patternLetraX.matcher(valor.toUpperCase());

		//valido
		//return matcher.matches();
		// É um número inteiro ou é X
		return (matcher.matches() || matcherLetraX.matches());
	}

	public static boolean isNumeroInteiroDiferenteZero(String valor) {
		if (valor == null)
			return false;
		Pattern patternInteiro = Pattern.compile("[0-9]+");
		Matcher matcherInteiro = patternInteiro.matcher(valor);

		Pattern patternZero = Pattern.compile("[0]+");
		Matcher matcherZero = patternZero.matcher(valor);

		// É um número inteiro e diferente de zero?
		return (matcherInteiro.matches() && !matcherZero.matches());
	}

	/**
	 * Substitui parâmetros do tipo {0}, {1}, e assim sucessivamente, por uma lista de argumentos.
	 * 
	 * @author andre.mello
	 * @param msg parametrizavel.
	 * @param argumentos para substituir parâmetros do tipo {0}, {1}, etc.
	 * @return mensagem montada.
	 */
	public static String montarMensagem(String msg, String... argumentos) {

		for (Integer x = 0; x < argumentos.length; x++) {

			msg = msg.replace("{0}".replace("0", x.toString()), argumentos[x]);
		}

		return msg;
	}

}
