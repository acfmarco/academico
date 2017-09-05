/**
 * 
 */
package br.com.iasc.utils.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import br.com.iasc.utils.UtilJava;

/**
 *
 * @author C1257320
 * @since 21/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public abstract class UtilData {

	public static final String DIA_MES_ANO_HORA_MIN_SEGUNDO = "dd/MM/yyyy HH:mm:ss";

	public static final String DIA_MES_ANO = "dd/MM/yyyy";

	public static final String MES_ANO = "MM/yyyy";

	/**
	 * Retorna a data passada como argumento com a ultima hora do dia.
	 * 
	 * @param data - pode ser uma data inícial ou final de acordo com o caso.
	 * @return Date
	 */
	public static Date getUltimaHoraDia(Date data) {
		Calendar dataFim = Calendar.getInstance();
		dataFim.setTime(data);
		if (dataFim.get(Calendar.AM_PM) == 0) {
			dataFim.set(Calendar.AM_PM, Calendar.AM);
		} else {
			dataFim.set(Calendar.AM_PM, Calendar.PM);
		}
		dataFim.set(Calendar.HOUR, 23);
		dataFim.set(Calendar.MINUTE, 59);
		dataFim.set(Calendar.SECOND, 59);
		return dataFim.getTime();
	}

	/**
	 * Retorna a data passada como argumento com a primeira hora do dia.
	 * 
	 * @param dataInicial
	 * @return Date
	 */
	public static Date getPrimeiraHoraDia(Date dataInicial) {
		Calendar dataInicio = Calendar.getInstance();
		dataInicio.setTime(dataInicial);
		dataInicio.set(Calendar.AM_PM, Calendar.AM);
		dataInicio.set(Calendar.HOUR, 0);
		dataInicio.set(Calendar.MINUTE, 0);
		dataInicio.set(Calendar.SECOND, 0);
		return dataInicio.getTime();
	}

	/**
	 * Tranforma o dia da data passada como argumento para o primeiro dia do mês.
	 * 
	 * @param data
	 * @return Date
	 */
	public static Date getDataCompetencia(Date data) {

		if (data != null) {
			Calendar dataCompetencia = Calendar.getInstance();
			dataCompetencia.set(getAno(data), getMes(data), 1);
			return dataCompetencia.getTime();
		}
		return null;
	}

	public static Date adicionarDiasData(Date data, Integer qtdDias) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, qtdDias);

		return calendar.getTime();
	}

	public static Date getMesAnterior(Date data) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);

		return calendar.getTime();
	}

	/**
	 * Retorna uma data no dia 1, com mes e ano como argumento
	 * @param mes
	 * @param ano
	 * @return
	 */
	public static Date adicionarDiaInicial(int mes, int ano) {

		Calendar data = Calendar.getInstance();
		data.set(Calendar.MONTH, mes - 1);
		data.set(Calendar.YEAR, ano);
		data.set(Calendar.DATE, 01);
		data.set(Calendar.HOUR_OF_DAY, 0);
		data.set(Calendar.MINUTE, 0);
		data.set(Calendar.SECOND, 0);
		data.set(Calendar.MILLISECOND, 0);

		return data.getTime();
	}

	/**
	 * Calcula a data de aposentadoria.
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
	 * Calcula a diferença de dias entre duas datas.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Integer
	 */
	public static Integer calcularDiferencaEmDias(Date dataInicial, Date dataFinal) {
		long diferenca = dataFinal.getTime() - dataInicial.getTime();
		int tempoDia = 1000 * 60 * 60 * 24;
		return (int) (diferenca / tempoDia);
	}

	/**
	 * Calcula a diferença de meses entre duas datas.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Integer
	 */
	public static Integer calcularDiferencaEmMeses(Date dataInicial, Date dataFinal) {
		long diferenca = dataFinal.getTime() - dataInicial.getTime();
		int tempoMes = 1000 * 60 * 60 * 24 * 30;
		return (int) (diferenca / tempoMes);
	}

	/**
	 * Calcula a diferença de anos entre duas datas.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Integer
	 */
	public static Integer calcularDiferencaEmAnos(Date dataInicial, Date dataFinal) {
		long diferenca = dataFinal.getTime() - dataInicial.getTime();
		int tempoAno = 1000 * 60 * 60 * 24 * 30 * 12;
		return (int) (diferenca / tempoAno);
	}

	/**
	 * Calcula a idade em anos entre duas datas.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Integer
	 */
	public static Integer calcularIdadeNaDataInscricao(Date dataInicial, Date dataFinal) {

		Integer idade = 0;

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(dataInicial);

		Calendar dataInscricao = Calendar.getInstance();
		dataInscricao.setTime(dataFinal);

		int anoNascimento = dataNascimento.get(Calendar.YEAR);
		int mesNascimento = dataNascimento.get(Calendar.MONTH);
		int diaNascimento = dataNascimento.get(Calendar.DAY_OF_MONTH);

		int anoInscricao = dataInscricao.get(Calendar.YEAR);
		int mesInscricao = dataInscricao.get(Calendar.MONTH);
		int diaInscricao = dataInscricao.get(Calendar.DAY_OF_MONTH);

		idade = anoInscricao - anoNascimento;
		if ((mesInscricao < mesNascimento) || ((mesInscricao == mesNascimento) && (diaInscricao < diaNascimento))) {
			idade -= 1;
		}
		return idade;

	}

	/**
	 * Calcula a diferença de horas entre duas datas.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Integer
	 */
	public static Integer calcularDiferencaEmHoras(Date dataInicial, Date dataFinal) {
		long diferenca = dataFinal.getTime() - dataInicial.getTime();
		int tempoHora = 1000 * 60 * 60;
		return (int) (diferenca / tempoHora);
	}

	/**
	 * Retorna a maior data das três datas passadas como argumento.
	 * 
	 * @param data1
	 * @param data2
	 * @param data3
	 * @return Date
	 */
	public static Date comparaMaiorData(Date data1, Date data2, Date data3) {

		if (data1 != null && data2 == null && data3 == null) {
			return data1;
		}
		if (data1 == null && data2 != null && data3 == null) {
			return data2;
		}
		if (data1 == null && data2 == null && data3 != null) {
			return data3;
		}
		if ((data2 == null || (data1 != null && data1.after(data2))) && (data3 == null || (data1 != null && data1.after(data3)))) {
			return data1;
		}
		if ((data1 == null || (data2 != null && data2.after(data1))) && (data3 == null || (data2 != null && data2.after(data3)))) {
			return data2;
		}
		if ((data1 == null || (data3 != null && data3.after(data1))) && (data2 == null || (data3 != null && data3.after(data2)))) {
			return data3;
		}
		return null;
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
	 * Método resopnsável por calcular a idade através da data de aposentadoria.
	 * 
	 * @param nascimento
	 * @param dataAposentadoria
	 * @return Integer
	 */
	public static Integer calcularIdadePorDataAposentadoria(Date nascimento, Date dataAposentadoria) {
		Integer idade = 0;

		Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(nascimento);

		Calendar aposentadoria = Calendar.getInstance();
		aposentadoria.setTime(dataAposentadoria);

		int anoNascimento = dataNascimento.get(Calendar.YEAR);
		int mesNascimento = dataNascimento.get(Calendar.MONTH);
		int diaNascimento = dataNascimento.get(Calendar.DAY_OF_MONTH);

		int anoAposentadoria = aposentadoria.get(Calendar.YEAR);
		int mesAposentadoria = aposentadoria.get(Calendar.MONTH);
		int diaAposentadoria = aposentadoria.get(Calendar.DAY_OF_MONTH);

		idade = anoAposentadoria - anoNascimento;
		if ((mesAposentadoria < mesNascimento) || ((mesAposentadoria == mesNascimento) && (diaAposentadoria < diaNascimento))) {
			idade -= 1;
		}
		return idade;
	}

	/**
	 * Verificar se data formatada é uma data valida.
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean isDataValida(Date date) {

		if (date == null) {
			return false;
		} else {

			String data = formataDataPorPadrao(date, DIA_MES_ANO);
			return isDataFormatadaValida(data);

		}
	}

	/**
	 * Retorna true (verdadeio) se a data passado como parametro for menor ou igual ao ano 1900
	 * Ex: Data menor do que 01/01/1900
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean isDataMenor1900(Date data) {

		if (data == null) {
			throw new IllegalArgumentException("A Data informada é nula.");
		}

		Calendar calendario = Calendar.getInstance();
		calendario.set(1900, Calendar.JANUARY, 1);

		return isDataMenorOuIgual(data, calendario.getTime());
	}

	/**
	 * Verifica se a data formatada é uma data valida.
	 * 
	 * @param data
	 * @return bolean
	 */
	public static boolean isDataFormatadaValida(String data) {
		boolean retorno = true;
		if (UtilJava.isStringVazia(data)) {

			return false;

		} else {

			String datax = data.replace("/", "");
			Integer dia = Integer.parseInt(datax.substring(0, 2));
			Integer mes = Integer.parseInt(datax.substring(2, 4));
			Integer ano = Integer.parseInt(datax.substring(4, 8));

			// verifica o dia valido para cada mes
			if ((dia < 01) || (dia < 01 || dia > 30) && (mes == 04 || mes == 06 || mes == 9 || mes == 11) || dia > 31) {
				retorno = false;
			}

			// verifica se o mes e valido
			if (mes < 01 || mes > 12) {
				retorno = false;
			}

			// verifica se e ano bissexto
			if (mes == 2 && (dia < 01 || dia > 29 || (dia > 28 && (ano / 4) != ano / 4))) {
				retorno = false;
			}
		}
		return retorno;
	}

	public static boolean isDataAnoMesValida(String dataAnoMes) {
		boolean retorno = true;

		if (UtilJava.isStringVazia(dataAnoMes)) {

			return false;

		} else {

			String ano = dataAnoMes.substring(0, 4);
			if (!validarAno(ano)) {
				retorno = false;
			}

			Integer mes = Integer.parseInt(dataAnoMes.substring(4, 6));
			if (!validarMes(mes)) {
				retorno = false;
			}
		}
		return retorno;
	}

	public static boolean isDataMesAnoValida(String dataMesAno) {
		boolean retorno = true;

		if (UtilJava.isStringVazia(dataMesAno)) {
			return false;
		} else {

			Integer ano = Integer.parseInt(dataMesAno.substring(3, dataMesAno.length()));
			if (!validarAno(ano)) {
				retorno = false;
			}

			Integer mes = Integer.parseInt(dataMesAno.substring(0, 2));
			if (!validarMes(mes)) {
				retorno = false;
			}
		}
		return retorno;
	}

	private static boolean validarMes(Integer mes) {
		boolean retorno = true;
		if (mes < 01 || mes > 12) {
			retorno = false;
		}
		return retorno;
	}

	private static boolean validarAno(String ano) {
		boolean retorno = true;
		if (ano.length() != 4) {
			retorno = false;
		}
		return retorno;
	}

	private static boolean validarAno(Integer ano) {
		boolean retorno = true;
		if (ano < 1900) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Transforma a data formatada para o tipo Date.
	 * 
	 * @param valor
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseToDate(String valor) throws ParseException {
		return parseToDate(valor, DIA_MES_ANO);
	}

	/**
	 * Transforma a data formatada para o tipo Date no formato indicado.
	 * 
	 * @param valor
	 * @param padrao
	 * @return
	 * @throws ParseException
	 */
	public static Date parseToDate(String valor, String padrao) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(padrao);
		sdf.setLenient(false);
		return sdf.parse(valor);
	}
	
	/**
	 * Retorna a data passada como argumento formatada.  
	 * Ex. 09/02/2012 16:41:16
	 * 
	 * @param data
	 * @return String
	 */
	public static String formataDataEHora(Date data) {
		if (data == null) {
			return null;
		}
		return formataDataPorPadrao(data, "dd/MM/yyyy à's' HH:mm:ss");
	}

	/**
	 * Retorna a data passada como argumento formatada.  
	 * Ex. 09/02/2012 16:41:16
	 * 
	 * @param data
	 * @return String
	 */
	public static String formataMesEAno(Date data) {
		if (data == null) {
			return null;
		}
		return formataDataPorPadrao(data, "MM/yyyy");
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
	 * 
	 * @param dataInicio
	 * @param dataFinal
	 * @return
	 */
	public static boolean dataAnterior(Date dataInicio, Date dataFinal) {
		return dataInicio.before(dataFinal);
	}

	/**
	 * Verifica se uma data passada é igual a outra
	 *
	 * @@param dataInicio
	 *            java.util.Date
	 * @@param dataFinal
	 *            java.util.Date
	 * @@return boolean
	 * @@see java.util.Date
	 */
	public static boolean dataIgual(Date dataInicio, Date dataFinal) {
		return dataInicio.equals(dataFinal);
	}

	/**
	 * Verifica se uma data passada é menor ou igual que a outra
	 *
	 * @@param dataInicio
	 *            java.util.Date
	 * @@param dataFinal
	 *            java.util.Date
	 * @@return boolean
	 * @@see java.util.Date
	 */
	public static boolean dataMenorOuIgual(Date dataInicio, Date dataFinal) {
		return dataAnterior(dataInicio, dataFinal) || dataIgual(dataInicio, dataFinal);
	}

	/**
	 * Verifica se uma data passada é maior ou igual que a outra
	 *
	 * @@param dataInicio
	 *            java.util.Date
	 * @@param dataFinal
	 *            java.util.Date
	 * @@return boolean
	 * @@see java.util.Date
	 */
	public static boolean dataMaiorOuIgual(Date dataInicio, Date dataFinal) {
		return dataMaior(dataInicio, dataFinal) || dataIgual(dataInicio, dataFinal);
	}

	/**
	 * Verifica se uma data passada esta entre um período de duas outras datas
	 *
	 * @@param dataEntre
	 *            java.util.Date
	 * @@param dataInicio
	 *            java.util.Date
	 * @@param dataFinal
	 *            java.util.Date
	 * @@return boolean
	 * @@see java.util.Date
	 */
	public static boolean dataEntrePeriodo(Date dataEntre, Date dataInicio, Date dataFinal) {
		return dataMaiorOuIgual(dataEntre, dataInicio) && dataMenorOuIgual(dataEntre, dataFinal);
	}

	/**
	 * Verifica se uma data passada é maior que a outra
	 *
	 * @@param dataInicio
	 *            java.util.Date
	 * @@param dataFinal
	 *            java.util.Date
	 * @@return boolean
	 * @@see java.util.Date
	 */
	public static boolean dataMaior(Date dataInicio, Date dataFinal) {
		return dataInicio.after(dataFinal);
	}

	/**
	 * 
	 * @return
	 */
	public static Date getDataZerada() {
		try {
			return UtilData.parseToDate("00/00/0000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Compara se o objeto dataInicio é igual que o objeto dataFim, ignorando as
	 * horas.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return boolean
	 */
	public static boolean isDataIgual(Date dataInicio, Date dataFim) {

		Calendar inicio = Calendar.getInstance();
		Calendar fim = Calendar.getInstance();

		inicio.setTime(dataInicio);
		fim.setTime(dataFim);

		if (inicio.get(Calendar.DAY_OF_MONTH) == fim.get(Calendar.DAY_OF_MONTH) && inicio.get(Calendar.MONTH) == fim.get(Calendar.MONTH) && inicio.get(Calendar.YEAR) == fim.get(Calendar.YEAR)) {
			return true;
		}
		return false;
	}

	/**
	 * Compara se o objeto dataInicio é menor que o objeto dataFim, ignorando as
	 * horas.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return boolean
	 */
	public static boolean isDataMenor(Date dataInicio, Date dataFim) {

		Calendar inicio = Calendar.getInstance();
		Calendar fim = Calendar.getInstance();

		inicio.setTime(dataInicio);
		fim.setTime(dataFim);

		if (inicio.get(Calendar.YEAR) < fim.get(Calendar.YEAR)) {
			return true;
		}
		if (inicio.get(Calendar.YEAR) == fim.get(Calendar.YEAR)) {

			if (inicio.get(Calendar.MONTH) < fim.get(Calendar.MONTH)) {
				return true;
			}
			if (inicio.get(Calendar.MONTH) == fim.get(Calendar.MONTH) && inicio.get(Calendar.DAY_OF_MONTH) < fim.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compara se o objeto dataInicio é maior que o objeto dataFim, ignorando as
	 * horas.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return boolean
	 */
	public static boolean isDataMaior(Date dataInicio, Date dataFim) {

		Calendar inicio = Calendar.getInstance();
		Calendar fim = Calendar.getInstance();

		inicio.setTime(dataInicio);
		fim.setTime(dataFim);

		if (inicio.get(Calendar.YEAR) > fim.get(Calendar.YEAR)) {
			return true;
		}
		if (inicio.get(Calendar.YEAR) == fim.get(Calendar.YEAR)) {

			if (inicio.get(Calendar.MONTH) > fim.get(Calendar.MONTH)) {
				return true;
			}
			if (inicio.get(Calendar.MONTH) == fim.get(Calendar.MONTH) && inicio.get(Calendar.DAY_OF_MONTH) > fim.get(Calendar.DAY_OF_MONTH)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compara se o objeto dataInicio é menor ou igual ao objeto dataFim, ignorando as
	 * horas.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return boolean
	 */
	public static boolean isDataMenorOuIgual(Date dataInicio, Date dataFim) {

		return isDataMenor(dataInicio, dataFim) || isDataIgual(dataInicio, dataFim);
	}

	/**
	 * Compara se o objeto dataInicio é maior ou igual ao objeto dataFim, ignorando as
	 * horas.
	 * 
	 * @param dataInicio
	 * @param dataFim
	 * @return boolean
	 */
	public static boolean isDataMaiorOuIgual(Date dataInicio, Date dataFim) {

		return isDataMaior(dataInicio, dataFim) || isDataIgual(dataInicio, dataFim);
	}

	/**
	 * Verificar se data formatada é uma data valida.
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean isDataValida(String data) {

		boolean retorno = true;
		if (UtilJava.isStringVazia(data)) {
			return false;
		}

		String datax = data.replace("/", "");
		Integer dia = Integer.parseInt(datax.substring(0, 2));
		Integer mes = Integer.parseInt(datax.substring(2, 4));
		Integer ano = Integer.parseInt(datax.substring(4, 8));

		// verifica o dia valido para cada mes
		if ((dia < 01) || (dia < 01 || dia > 30) && (mes == 04 || mes == 06 || mes == 9 || mes == 11) || dia > 31) {
			retorno = false;
		}

		// verifica se o mes e valido
		if (mes < 01 || mes > 12) {
			retorno = false;
		}

		// verifica se e ano bissexto
		if (mes == 2 && (dia < 01 || dia > 29 || (dia > 28 && (ano / 4) != ano / 4))) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Recebe uma string que representa uma data no formato ddMMyyyy, e retorna no formato dd/MM/yyyy. 
	 * 
	 * @return 
	 * 		  String
	 */
	public static String adicionaBarras(String data) {

		String dataTemp = null;
		dataTemp = data.substring(0, 2);
		dataTemp += "/";
		dataTemp += data.substring(2, 4);
		dataTemp += "/";
		dataTemp += data.substring(4, 8);

		return dataTemp;
	}

	/**
	 * Metodo responsevel por converter a data no formatato yyyy-MM-dd
	 * para o formato dd/MM/yyyy
	 * 
	 * @param data
	 * @return String data
	 */
	public static String converterData(String data) {

		if (data != null && data.length() > 12) {

			data = data.substring(0, 10);

			SimpleDateFormat formatIso = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");

			Date date;

			String retorno = "";

			try {

				date = formatIso.parse(data);
				retorno = formatOut.format(date);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return retorno;
		}

		return data;
	}

	/**
	 * Retorna o ano data data passada como argumento.
	 *
	 * @param data
	 * @return Integer
	 */
	public static Integer getAno(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Retorna o mes da data passada como argumento.
	 * 
	 * @param data
	 * @return Integer
	 */
	public static Integer getMes(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * retorna a data no formato MM/yyyy
	 * 
	 * @param data
	 * @return String
	 */
	public static String getMesAno(Date data) {
		if (data != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
			return sdf.format(data);
		}
		return "";
	}

	/**
	 * Método que verifica se uma data (String) esta no padrão de mascara brasileira.
	 *  
	 * @param data
	 * @param regexMascara
	 * @return
	 */
	public static boolean isMascaraDataValidaBr(String data) {
		return isMascaraDataValida(data, "([0-9]{2})/([0-9]{2})/([0-9]{4})");
	}

	private static boolean isMascaraDataValida(String data, String regexMascara) {
		return Pattern.compile(regexMascara).matcher(data).matches();
	}

	/**
	 * Método que recebe uma string e ser for uma string vazia retorna null,
	 * se form uma data válida retorna um objeto do tipo Date,
	 * e se string de data inválida levanta um ParseException;
	 *  
	 * Método esta sendo utilizado para unificar a regra utilizada no converter e no validate.
	 * 
	 * @param strData
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDataValida(String strData) throws ParseException {
		if (UtilJava.isStringVazia(strData.trim())) {
			return null;
		}

		if (isMascaraDataValidaBr(strData)) {
			return parseToDate(strData);
		} else {
			throw new ParseException("Data (" + strData + ") inválida.", 0);
		}
	}

	public static Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static int getDayByDate(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(GregorianCalendar.DAY_OF_MONTH);
	}

	public static int getMonthByDate(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(GregorianCalendar.MONTH);
	}

	public static int getYearByDate(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(GregorianCalendar.YEAR);
	}

	public static String calcularTempo(Date inicio, Date fim) {

		StringBuilder sb = new StringBuilder();
		long diferenca = fim.getTime() - inicio.getTime();

		Double diferencaEmHoras = (double) (diferenca / (1000 * 60 * 60));
		if (diferenca > 0) {
			sb.append(diferencaEmHoras.intValue());
			sb.append(" Horas");
		}

		double minutosRestante = diferencaEmHoras * 60;
		Double diferencaEmMinutos = (double) (((diferenca / 1000) / 60) - minutosRestante);
		if (diferencaEmMinutos > 0) {
			sb.append(" e ");
			sb.append(diferencaEmMinutos.intValue());
			sb.append(" minutos");
		}

		long diferencaEmSegundos = (diferenca / 1000) % 60; //calcula os segundos restantes
		if (diferencaEmSegundos > 0) {
			sb.append(" e ");
			sb.append(diferencaEmSegundos);
			sb.append(" segundos");
		}

		return sb.toString();
	}

	/**
	 * Retorna a data passada como argumento formatada, através do formato padrao passado como argumento. Ex. 09/02/2012 16:41:16
	 * 
	 * @param data
	 * @param padrao
	 * @return String
	 */
	public static String formataDataPorPadrao(Date data, String padrao) {
		if (padrao.isEmpty()) {
			padrao = DIA_MES_ANO;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(padrao);
		String dataFormatada = sdf.format(data);
		return dataFormatada;
	}
}
