package br.com.iasc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;

import br.com.iasc.utils.dto.ObjetoSql;
import br.com.iasc.utils.exception.InfraException;
import br.com.iasc.utils.exception.AcademicoException;

import com.google.common.collect.Lists;

/**
 * Classe utilitária para verificação de coleções, stirng, e formatação de data.
 * 
 * @author tiago.menezes
 * @since 05/10/2012
 * 
 *        Copyright notice (c) 2012 BBPrevidência S/A
 */
public abstract class UtilJava {

	public static final String ANO_MES_DIA_HORA_MIN_SEGUNDO = "yyyy-MM-dd HH:mm:ss";

	public static final String DIA_MES_ANO_HORA_MIN_SEGUNDO = "dd/MM/yyyy HH:mm:ss";

	public static final String DIA_MES_ANO = "dd/MM/yyyy";

	public static final String STRING_VAZIA = " ";

	/**
	 * Verifica se a string passado como arguemnto está nula ou vazia, caso
	 * positivo retorna true caso contrário é retornado false.
	 * 
	 * @param obj
	 *            objeto a ser testado.
	 * @return boolean retorna true se vazia e false, caso contrário.
	 */
	public static boolean isStringVazia(String obj, Boolean remocaoEspacoBranco) throws NullPointerException {
		if (remocaoEspacoBranco) {

			try {

				obj = removerEspacoBranco(obj);

				if (obj.equals(" "))
					return true;

				else
					return isStringVazia(obj);

			} catch (NullPointerException e) {

				return true;

			}

		} else {

			return isStringVazia(obj);

		}

	}

	/**
	 * Verifica se a string passado como arguemnto está nula ou vazia, caso
	 * positivo retorna true caso contrário é retornado false.
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isStringVazia(String obj) {
		return obj == null || "".equals(obj);
	}

	/**
	 * Verifica se a colecao passado como arguemnto está nula ou vazia, caso
	 * positivo retorna true caso contrário é retornado false.
	 * 
	 * @param colecao
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isColecaoVazia(Collection colecao) {
		return colecao == null || colecao.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isColecaoDiferenteDeVazia(Collection colecao) {
		return colecao != null && !colecao.isEmpty();
	}

	/**
	 * Verifica se o array passado como argumento está nulo ou vazio. caso
	 * positivo retorna true caso contrário é retornado false.
	 * 
	 * @param array
	 * @return boolean
	 */
	public static boolean isArrayVazia(Object[] array) {
		return array == null || array.length <= 0;
	}

	/**
	 * Verifica se o valor número está nulo ou com valor igual a zero.
	 * 
	 * @param valor
	 * @return boolean
	 */
	public static boolean isNumeroNulo(Number valor) {
		if (valor == null) {
			return true;
		}
		if (valor instanceof BigDecimal && (valor.doubleValue() == 0.0)) {
			return true;
		}
		if (valor instanceof Double && valor.equals(new Double(0))) {
			return true;
		}
		if (valor instanceof Long && valor.equals(new Long(0))) {
			return true;
		}
		if (valor instanceof Integer && valor.equals(new Integer(0))) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna os primeiros nove digitos do cpf.
	 * 
	 * @param cpf
	 * @return String
	 */
	public static String getNumeroCPF(String cpf) {
		String numero = cpf.substring(0, cpf.length() - 2);
		return numero;
	}

	/**
	 * Retorna os dois ultimos digitos do cpf.
	 * 
	 * @param cpf
	 * @return String
	 */
	public static String getDigitoCPF(String cpf) {
		String digito = cpf.substring(cpf.length() - 2, cpf.length());
		return digito;
	}

	/**
	 * Adiciona zero a equerda.
	 * 
	 * @param quantidade
	 * @param numero
	 * @return String
	 */
	public static String adicionaZeroEquerda(Integer quantidade, Integer numero) {
		if (numero != null && quantidade != null) {
			return String.format("%0" + quantidade + "d", numero);
		}
		return "";
	}

	/**
	 * Adiciona zero a equerda.
	 * 
	 * @param quantidade
	 * @param numero
	 * @return String
	 */
	public static String adicionaZeroEsquerda(Integer quantidade, Long numero) {
		if (numero != null && quantidade != null) {
			return String.format("%0" + quantidade + "d", numero);
		}
		return "";
	}

	/**
	 * Remove erros de sql injection do parametro pasasdo como argumento;
	 * 
	 * @param valor
	 * @return String
	 */
	public static String removeSQLInjection(String valor) {
		if (!isStringVazia(valor)) {
			String valorConvertido = valor.replaceAll("^[\\W]+", "");
			return valorConvertido;
		}
		return valor;
	}

	/**
	 * Remove itens duplicado da coleção passado como argumento.
	 * 
	 * @param colecao
	 * @return Collection<Object>
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" })
	public static Collection<?> removeItensDuplicados(Collection colecao) {
		Set hashSet = new HashSet();
		for (Object obj : colecao) {
			hashSet.add(obj);
		}
		ArrayList lista = new ArrayList();
		for (Object object : hashSet) {
			lista.add(object);
		}
		return lista;
	}

	/**
	 * Método resopnsável por calcular a idade através da data de nascimento.
	 * 
	 * @param nascimento
	 * @return Integer
	 */
	public static Integer calcularIdade(Date nascimento) {
		Integer idade = 0;

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(nascimento);

		int anoNascimento = dataNascimento.get(Calendar.YEAR);
		int mesNascimento = dataNascimento.get(Calendar.MONTH);
		int diaNascimento = dataNascimento.get(Calendar.DAY_OF_MONTH);

		Calendar dataAtual = Calendar.getInstance();
		int anoAtual = dataAtual.get(Calendar.YEAR);
		int mesAtual = dataAtual.get(Calendar.MONTH);
		int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);

		idade = anoAtual - anoNascimento;
		if ((mesAtual < mesNascimento) || ((mesAtual == mesNascimento) && (diaAtual < diaNascimento))) {
			idade -= 1;
		}
		return idade;
	}

	/**
	 * 
	 * @param idadeAposentadoria
	 * @param idadeAtual
	 * @param dataInscricao
	 * @return Date
	 */
	public static Date calcularDataAposentadoria(Integer idadeAposentadoria, Integer idadeAtual, Date dataInscricao) {

		Calendar inscricao = Calendar.getInstance();
		inscricao.setTime(dataInscricao);
		int diaInscricao = inscricao.get(Calendar.DAY_OF_MONTH);
		int mesInscricao = inscricao.get(Calendar.MONTH);

		int diferenca = idadeAposentadoria - idadeAtual;

		Calendar dataAposentadoria = Calendar.getInstance();
		dataAposentadoria.set(Calendar.DAY_OF_MONTH, diaInscricao);
		dataAposentadoria.set(Calendar.MONTH, mesInscricao);

		dataAposentadoria.add(Calendar.YEAR, diferenca - 1);

		if (dataAposentadoria.before(Calendar.getInstance().getTime())) {
			return Calendar.getInstance().getTime();
		} else {
			return dataAposentadoria.getTime();
		}
	}

	/**
	 * Método responsável por obter a diferença de anos entre a dataInicial e a
	 * dataFinal.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Integer
	 */
	public static Integer getDiferencaDeAnos(Date dataInicial, Date dataFinal) {

		Calendar calendarInicial = Calendar.getInstance();
		calendarInicial.setTime(dataInicial);

		Calendar calendarFinal = Calendar.getInstance();
		calendarFinal.setTime(dataFinal);

		return calendarFinal.get(Calendar.YEAR) - calendarInicial.get(Calendar.YEAR);
	}

	/**
	 * Retorna um objeto do tipo Date com data de primeiro de janeiro de hum mil
	 * e novecentos.
	 * 
	 * @return Date
	 */
	public static Date getDataMinima() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1900, 0, 1, 0, 0, 0);
		return calendar.getTime();
	}

	@SuppressWarnings("rawtypes")
	public static void updatePropertyFromObject(Object obj, String nomePropriedade, Object valor) throws InfraException {

		try {

			Class clazz = Class.forName(obj.getClass().getName().toString());
			Method[] m = clazz.getDeclaredMethods();

			for (int i = 0; i < m.length; i++) {
				String nomeMetodo = "set" + nomePropriedade;
				Method method = m[i];
				if (method.getName().equalsIgnoreCase(nomeMetodo)) {
					method.invoke(obj, valor);
					break;
				}
			}

		} catch (ClassNotFoundException e) {
			throw new InfraException(e);
		} catch (IllegalArgumentException e) {
			throw new InfraException(e);
		} catch (IllegalAccessException e) {
			throw new InfraException(e);
		} catch (InvocationTargetException e) {
			throw new InfraException(e);
		}
	}

	/**
	 * Remove todos os espaços em branco duplicados.
	 * 
	 * @param toNormalize
	 *            String com espaços em branco a serem removidos.
	 * @return String com espaços em branco duplicados removidos.
	 * @throws NullAttributeException
	 *             caso o atributo toNormalize seja passado nulo.
	 */
	public static String removerEspacoBranco(String toNormalize) throws NullPointerException {

		if (toNormalize == null)
			throw new NullPointerException("toNormalize não pode ser null.");

		else
			return toNormalize.replaceAll("\\s+", " ");

	}

	public static String trocaCaracter(String valor, char oldChar, char newChar) {
		String novo;
		novo = valor.replace(oldChar, newChar);
		return novo;
	}

	/**
	 * 
	 * Rotina que retira um determinado caracter de uma String.
	 * 
	 * @param valor
	 *            String com o caracter a ser retirado.
	 * @return "" se a String for vazia ou a String sem o caracter.
	 * 
	 */
	public static String retiraCaracter(String valor, char caracter) {
		StringBuffer sb = new StringBuffer(valor);

		int i = 0;
		while (i < sb.length()) {
			if (sb.charAt(i) == caracter) {
				sb.deleteCharAt(i);
			} else {
				i++;
			}
		}

		return sb.toString();
	}

	public static String removeChars(String str, String toBeRemoved) {

		char[] temp = str.toCharArray();
		String retorno = "";

		for (int i = 0; i < temp.length; i++) {

			if (!(toBeRemoved.indexOf(temp[i] + "") != -1)) {
				retorno = retorno + temp[i];
			}
		}

		return retorno;
	}

	/**
	 * 
	 * @return
	 * @param valor
	 */
	public static double stringToDouble(String valor) {
		double valorDouble = 0.0;

		if (valor != null) {
			try {
				valorDouble = Double.parseDouble(valor);
			} catch (NumberFormatException nfEx) {
				valorDouble = 0.0;
			}
		}

		return valorDouble;
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public static float stringToFloat(String valor) {
		return (float) stringToDouble(valor);
	}

	public static String getMimeTypeArquivoImagem(String nomeArquivo) {
		String mimeType = "", extensao;
		extensao = getExtensaoArquivo(nomeArquivo);

		if (extensao.equalsIgnoreCase("gif")) {
			mimeType = "image/gif";
		}

		if (extensao.equalsIgnoreCase("jpg") || extensao.equalsIgnoreCase("jepg")) {
			mimeType = "image/jpeg";
		}

		if (extensao.equalsIgnoreCase("png")) {
			mimeType = "image/png";
		}

		return mimeType;
	}

	public static String getExtensaoArquivo(String nomeArquivo) {

		String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf('.') + 1);

		return extensao;
	}

	/**
	 * Particiona uma lista em sub-listas de tamanho definido.
	 * 
	 * @param lista
	 * @param tamanho
	 * @return List of Listas
	 */
	public static <E> List<List<E>> particionarLista(List<E> lista, int tamanho) {
		return Lists.partition(lista, tamanho);
	}

	public static String trocaUltimaOcorrenciaCaracter(String string, String substring, String replacement) {
		int index = string.lastIndexOf(substring);
		if (index == -1)
			return string;
		return string.substring(0, index) + replacement + string.substring(index + substring.length());
	}

	/**
	 * Valida o digito verificador da agência bancaria.
	 * 
	 * @param numero
	 * @param digito
	 * @return boolean
	 */
	public static boolean validarNumeroAgencia(String numero, String digito) {

		if (StringUtils.isBlank(numero)) {
			throw new IllegalArgumentException("Informe o número da agência.");
		}
		if (StringUtils.isBlank(digito)) {
			throw new IllegalArgumentException("Informe o digíto da agência.");
		}

		return validarDigitoVerificadorModulo11(numero, digito);
	}

	/**
	 * Valida o digito verificador da conta corrente.
	 * 
	 * @param numero
	 * @param digito
	 * @return boolean
	 */
	public static boolean validarNumeroContaCorrente(String numero, String digito) {

		if (StringUtils.isBlank(numero)) {
			throw new IllegalArgumentException("Informe o número da conta corrente.");
		}
		if (StringUtils.isBlank(digito)) {
			throw new IllegalArgumentException("Informe o digíto da conta corrente.");
		}

		return validarDigitoVerificadorModulo11(numero, digito);
	}

	/**
	 * Validação do digito verificador - Módulo 11. 
	 * Válida somente para o Banco: 001 - Banco do Brasil. 	
	 * 
	 * @param numero
	 * @param digito
	 * @return boolean
	 */
	public static boolean validarDigitoVerificadorModulo11(String numero, String digito) {

		String digitoVerificador = "";

		int digitoModulo11 = modulo11(numero);

		if (digitoModulo11 == 10) {

			digitoVerificador = "X";

		} else if (digitoModulo11 == 11) {

			digitoVerificador = "0";

		} else {
			digitoVerificador = Integer.toString(digitoModulo11);
		}

		return StringUtils.equalsIgnoreCase(digitoVerificador, digito);
	}

	/**
	 * Realiza o cálculo do módulo 10.
	 *
	 * @param texto
	 * @return int
	 */
	public static int modulo10(String texto) {

		int peso = 2;
		int digito = 0;
		int acumulado = 0;
		char[] valores = texto.toCharArray();

		for (int i = (valores.length - 1); i >= 0; i--) {

			int valor = Integer.parseInt(String.valueOf(valores[i])) * peso;
			if (Integer.toString(valor).length() > 1) {

				for (int j = 0; j < Integer.toString(valor).length(); j++) {
					String numero = Integer.toString(valor).substring(j, j + 1);
					acumulado += Integer.parseInt(numero);
				}
			} else {
				acumulado += valor;
			}
			peso = (peso == 2) ? 1 : 2;
		}

		int modulo = (acumulado % 10);
		digito = 10 - modulo;
		return digito == 10 ? 0 : digito;
	}

	/**
	 * Realiza o cálculo do módulo 11.
	 * Deve ser passado somente o numero sem o digito veririfcador.
	 * EX: Nº Conta Corrente "37461-X", "6355-X", "50749-0", "182330-2"
	 * 
	 * @param texto
	 * @return int
	 */
	public static int modulo11(String texto) {

		int peso = 2;
		int digito = 0, modulo = 0, total = 0;
		char[] valores = texto.toCharArray();

		for (int i = (valores.length - 1); i >= 0; i--) {
			int valor = Integer.parseInt(String.valueOf(valores[i])) * peso;
			total += valor;
			peso++;
		}

		if (total >= 11) {
			modulo = total % 11;
			digito = 11 - modulo;
		} else {
			digito = 11 - total;
		}
		return digito;
	}

	/**
	 * Metodo responsavel por monstar os parâmetros SQL em uma determinada string
	 * 
	 * @param parametro
	 * @return {@link String}
	 */

	public static String montarSQL(Collection<ObjetoSql> parametro) {

		StringBuilder retorno = new StringBuilder();

		String constanteWhere = " WHERE ";
		String constanteAND = " AND ";

		int contador = 0;

		for (ObjetoSql objetoSql : parametro) {

			if (contador == 0) {
				retorno.append(constanteWhere + objetoSql.getAtalho() + "." + objetoSql.getNomeCampo() + "=" + objetoSql.getCodigo());
			} else {
				retorno.append(constanteAND + objetoSql.getAtalho() + "." + objetoSql.getNomeCampo() + "=" + objetoSql.getCodigo());
			}

			contador++;
		}

		return retorno.toString();
	}

	/**
	 * Retira os caracteres especiais do cpf.
	 * 
	 * @param cpfFormatado
	 * @return Long
	 */
	public static Long desformataCPF(String cpfFormatado) {
		String cpf = cpfFormatado.replace(".", "").replace("-", "");
		return Long.valueOf(cpf);
	}

	/**
	 * Formata o cpf de acordo com a mascara 999.999.999-99
	 * 
	 * @param cpf
	 * @return String
	 */
	public static String formataCPF(String cpf) {
		try {
			String cpfCompleto = "";
			if (isStringVazia(cpf)) {
				return cpf;
			}

			if (cpf.length() < 11) {
				NumberFormat nf = NumberFormat.getIntegerInstance();
				nf.setMinimumIntegerDigits(11);
				nf.setGroupingUsed(false);
				cpfCompleto = nf.format(Long.valueOf(cpf));
			} else {
				cpfCompleto = cpf;
			}

			MaskFormatter mf = new MaskFormatter("###.###.###-##");
			mf.setValueContainsLiteralCharacters(false);

			return mf.valueToString(cpfCompleto);

		} catch (ParseException e) {
			throw new AcademicoException(e);
		}
	}
	
	/**
	 * Retorna o objeto calendar passado como argumento formatada, através do formato padrao passado como argumento. Ex. 09/02/2012 16:41:16
	 * 
	 * @param calendar
	 * @param padrao
	 * @return String
	 */
	public static String formataCalendarPorPadrao(Calendar calendar, String padrao) {
		if (isStringVazia(padrao)) {
			padrao = DIA_MES_ANO;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(padrao);
		String dataFormatada = sdf.format(calendar.getTime());
		return dataFormatada;
	}
	
	/**
	 * Retorna a data passada como argumento formatada, através do formato padrao passado como argumento. Ex. 09/02/2012 16:41:16
	 * 
	 * @param data
	 * @param padrao
	 * @return String
	 */
	public static String formataDataPorPadrao(Date data, String padrao) {
		if (isStringVazia(padrao)) {
			padrao = DIA_MES_ANO;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(padrao);
		String dataFormatada = sdf.format(data);
		return dataFormatada;
	}
	
	/**
	 * Converte um número no formato "###.###.###,00" para um double simples
	 * 
	 * @param valor String
	 * @return double
	 */
	public static float desformataNumero(final String valor) {
		String vlr = valor;
		vlr = removeChars(vlr, "R$ ");
		vlr = removeChars(vlr, ".");
		vlr = trocaCaracter(vlr, ',', '.');

		return stringToFloat(vlr);
	}
}