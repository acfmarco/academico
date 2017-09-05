package br.com.iasc.utils.formatacao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class FormatarMonetarios {

	/**
	 * Locale Brasileiro
	 */
	private static final Locale BRAZIL = new Locale("pt", "BR");

	/**
	 * Simbolos especificos do Dolar Americano
	 */
	private static final DecimalFormatSymbols DOLAR = new DecimalFormatSymbols(Locale.US);

	/**
	 * Simbolos especificos do Euro
	 */
	private static final DecimalFormatSymbols EURO = new DecimalFormatSymbols(Locale.GERMANY);

	/**
	 * Simbolos especificos do Real Brasileiro
	 */
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);

	/**
	 * Mascara de dinheiro para Dolar Americano
	 */
	public static final DecimalFormat DINHEIRO_DOLAR = new DecimalFormat("¤ ###,###,##0.00", DOLAR);

	/**
	 * Mascara de dinheiro para Euro
	 */
	public static final DecimalFormat DINHEIRO_EURO = new DecimalFormat("¤ ###,###,##0.00", EURO);

	/**
	 * Mascara de dinheiro para Real Brasileiro
	 */
	public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);

	/**
	 * Mascara texto com formatacao monetaria
	 * 
	 * @param valor Valor a ser mascarado
	 * @param moeda Padrao monetario a ser usado
	 * @return Valor mascarado de acordo com o padrao especificado
	 */
	public static String mascaraDinheiro(double valor, DecimalFormat moeda) {
		return moeda.format(valor);
	}

	/**
	 * Mascara texto com formatacao monetaria
	 * 
	 * @return Valor mascarado de acordo com o padrao DINHEIRO_REAL
	 */
	public static String mascaraDinheiro(BigDecimal valor) {
		if (valor == null) {
			valor = BigDecimal.ZERO;
		}

		return DINHEIRO_REAL.format(valor);
	}

}
