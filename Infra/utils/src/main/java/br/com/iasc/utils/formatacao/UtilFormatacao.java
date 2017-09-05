package br.com.iasc.utils.formatacao;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.exception.InfraException;
import br.com.iasc.utils.mascara.TipoMascara;

/**
 *
 * @author Tiago Menezes
 * @since 20/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public abstract class UtilFormatacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5250586101348023341L;

	private static Logger logger = Logger.getLogger(UtilFormatacao.class);

	private static final String DIA_MES_ANO = "dd/MM/yyyy";

	/**
	 * Retorna a data passada como argumento formatada, através do formato
	 * padrao passado como argumento. Ex. 09/02/2012 16:41:16
	 * 
	 * @param data
	 * @param padraos
	 */
	public static String formataDataPorPadrao(Date data, String padrao) {
		if (UtilJava.isStringVazia(padrao)) {
			padrao = DIA_MES_ANO;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(padrao);
		String dataFormatada = sdf.format(data);
		return dataFormatada;
	}

	/**
	 * Retorna o objeto calendar passado como argumento formatada, através do
	 * formato padrao passado como argumento. Ex. 09/02/2012 16:41:16
	 * 
	 * @param calendar
	 * @param padrao
	 * @return String
	 */
	public static String formataCalendarPorPadrao(Calendar calendar, String padrao) {
		if (UtilJava.isStringVazia(padrao)) {
			padrao = DIA_MES_ANO;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(padrao);
		String dataFormatada = sdf.format(calendar.getTime());
		return dataFormatada;
	}

	/**
	 * Formata o cpf de acordo com a mascara 999.999.999-99
	 * 
	 * @param cpf
	 * @return String
	 * @throws InfraException 
	 */
	public static String formataCPF(String cpf) throws InfraException {
		String cpfCompleto = "";
		if (UtilJava.isStringVazia(cpf)) {
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

		try {
			MaskFormatter mf = new MaskFormatter("###.###.###-##");
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(cpfCompleto);

		} catch (ParseException e) {
			throw new InfraException(e);
		}
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
	 * Converte um número no formato "###.###.###,00" para um double simples
	 * 
	 * @param valor
	 *            String
	 * @return double
	 */
	public static float desformataNumero(final String valor) {
		String vlr = valor;
		vlr = UtilJava.removeChars(vlr, "R$ ");
		vlr = UtilJava.removeChars(vlr, ".");
		vlr = UtilJava.trocaCaracter(vlr, ',', '.');

		return UtilJava.stringToFloat(vlr);
	}

	/**
	 * Converte um número para String no formato "###.###.###,00"
	 * 
	 * @param valor
	 *            float
	 * @param casasDecimais
	 *            int
	 * @return String
	 * @see java.util.Locale
	 * @see java.text.NumberFormat
	 * @see java.text.DecimalFormat
	 */
	public static String formataNumero(float valor, int casasDecimais) {
		DecimalFormat formatador = (DecimalFormat) NumberFormat.getInstance(new Locale("pt", "Brazil"));

		if (casasDecimais > 0) {
			formatador.setDecimalSeparatorAlwaysShown(true);
		} else {
			formatador.setDecimalSeparatorAlwaysShown(false);
		}
		formatador.setMaximumFractionDigits(casasDecimais);
		formatador.setMinimumFractionDigits(casasDecimais);

		return formatador.format(Math.abs(valor));
	}

	/**
	 * 
	 *
	 * @param valorFormatar
	 * @return String
	 * @throws InfraException
	 */
	public static String formatarCPF(String valorFormatar) throws InfraException {

		Pattern pattern = null;
		Matcher matcher = null;
		String cpfFormatado = null;
		int zerosAEsquerda = 0;

		if (valorFormatar == null) {

			throw new InfraException("O valor valorFormatar não pode ser nulo.");

		} else {

			pattern = Pattern.compile(TipoMascara.CPF.getRegex());

			cpfFormatado = new String();
			zerosAEsquerda = 11 - valorFormatar.length();

			for (int x = zerosAEsquerda; x > 0; x--) {

				cpfFormatado += 0;
			}

			cpfFormatado += valorFormatar;

			matcher = pattern.matcher(cpfFormatado);
			cpfFormatado = matcher.replaceAll(TipoMascara.CPF.getMascara());
		}

		return cpfFormatado;

	}

	/**
	 * 
	 *
	 * @param valorFormatar
	 * @return
	 * @throws InfraException
	 */
	public static String formatarTelefoneSemDDD(String valorFormatar) throws InfraException {

		Pattern pattern = null;
		Matcher matcher = null;
		String telefoneFormatado = null;

		if (valorFormatar == null) {

			throw new InfraException("O valor valorFormatar não pode ser nulo.");

		} else {

			pattern = Pattern.compile(TipoMascara.TELEFONE_SEMDDD.getRegex());

			matcher = pattern.matcher(valorFormatar);
			telefoneFormatado = matcher.replaceAll(TipoMascara.TELEFONE_SEMDDD.getMascara());
		}

		return telefoneFormatado != null && !telefoneFormatado.isEmpty() ? telefoneFormatado : valorFormatar;

	}

	/**
	 * 
	 *
	 * @param valorFormatar
	 * @return
	 * @throws InfraException
	 */
	public static String formatarCep(String valorFormatar) throws InfraException {

		if (valorFormatar == null) {

			throw new InfraException("O valor valorFormatar não pode ser nulo.");

		} else {

			return valorFormatar.replaceAll(TipoMascara.CEP.getRegex(), TipoMascara.CEP.getMascara());

		}

	}

	/**
	 *
	 * @param valorFormatar
	 * @return String
	 * @throws InfraException
	 */
	public static String formatarDataDiaMesAno(String valorFormatar) throws InfraException {

		Pattern pattern = null;
		Matcher matcher = null;
		String dateFormatado = valorFormatar;

		if (valorFormatar == null) {

			throw new InfraException("O valor valorFormatar não pode ser nulo.");

		} else {

			pattern = Pattern.compile(TipoMascara.DATA_DIA_MES_ANO.getRegex());

			matcher = pattern.matcher(valorFormatar);
			dateFormatado = matcher.replaceAll(TipoMascara.DATA_DIA_MES_ANO.getMascara());
		}
		return dateFormatado;
	}

	/**
	 * 
	 * @param valorFormatar
	 * @return String
	 * @throws InfraException 
	 */
	public static String formatarValorMonetario(String valorFormatar) throws InfraException {
		Pattern pattern = null;
		Matcher matcher = null;

		String valor = valorFormatar;

		if (valorFormatar == null) {

			throw new InfraException("O valor valorFormatar não pode ser nulo.");

		} else {

			pattern = Pattern.compile(TipoMascara.MONETARIO.getRegex());

			matcher = pattern.matcher(valorFormatar);
			valor = matcher.replaceAll(TipoMascara.MONETARIO.getMascara());
		}
		return valor;

	}

	/**
	 * Método por verificar se a data é valida 
	 * 
	 * @param data
	 * @return boolean 
	 */

	public static String formatarDataString(String data) {

		String dataRecebida = "";

		if (data != null && !data.equalsIgnoreCase("") && data.length() > 9) {

			String dataLimpa = data.substring(0, 10);

			String ano = dataLimpa.substring(0, 4);
			String mes = dataLimpa.substring(5, 7);
			String dia = dataLimpa.substring(8, dataLimpa.length());

			dataRecebida = dia + "/" + mes + "/" + ano;
		}

		return dataRecebida;
	}

	/**
	 * 
	 *
	 * @param data
	 * @param formato
	 * @return String
	 * @throws InfraException
	 */
	public static String formatarData(String data, String formato) throws InfraException {

		Date dataConvertida = null;
		String dataRecebida = null;

		try {

			if (data != null && data.trim().length() >= 10 && !UtilJava.isStringVazia(formato)) {

				SimpleDateFormat dataFormato = new SimpleDateFormat(formato);

				dataConvertida = dataFormato.parse(data.substring(0, 10));
				dataRecebida = dataConvertida.toString();
			}

		} catch (ParseException e) {
			throw new InfraException(e);
		}
		return dataRecebida;
	}

	public static String validarEFormataCPF(String numero) {
		try {

			if (StringUtils.isNotBlank(numero)) {
				return formatarCPF(numero);
			}

		} catch (InfraException e) {
			logger.error(e);
		}
		return numero;
	}

}
