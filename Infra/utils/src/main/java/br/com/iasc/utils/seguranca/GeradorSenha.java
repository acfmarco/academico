package br.com.iasc.utils.seguranca;

import java.util.Random;

/**
 * Classe responsável por gerar a senha do usuário de acordo com o parametros de seguranças.
 * 
 * @author Tiago Menezes
 * @since 20/12/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public abstract class GeradorSenha {

	private static final char[] LETRAS_MAIUSCULAS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static final char[] LETRAS_MINUSCULAS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static final char[] NUMEROS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final char[] ESPECIAIS = { '!', '@', '#', '$', '%', '&', '*' };

	/**
	 * Método responsável por gerar a senha de acordo com a composição de senha 
	 * e tamanho da senha passados como argumentos.
	 * 
	 * @param tamanho
	 * @param composicaoSenha
	 * @return String
	 */
	public static String gerarSenha(int tamanho, String composicaoSenha) {

		String senha = "";

		int tamanhoComposicao = composicaoSenha.length();
		double qtdePorComposicao = Math.abs(tamanho / tamanhoComposicao);

		String[] tipo = composicaoSenha.split("");
		Random random = new Random();

		for (int i = 0; i < tipo.length; i++) {
			String composicao = tipo[i];

			if (composicao != null && !"".equals(composicao)) {

				Integer valor = Integer.parseInt(composicao);
				if (valor == 5) {

					for (int j = 0; j < tamanho; j++) {
						char t = NUMEROS[random.nextInt(NUMEROS.length)];
						senha = senha.concat(String.valueOf(t));
					}

				} else {

					switch (valor) {
					case 1:

						for (int j = 0; j < 1; j++) {
							char t = LETRAS_MAIUSCULAS[random.nextInt(LETRAS_MAIUSCULAS.length)];
							senha = senha.concat(String.valueOf(t));
						}
						break;
					case 2:

						if (i == (tipo.length - 1)) {
							qtdePorComposicao = tamanho - senha.length();
						}

						for (int j = 0; j < qtdePorComposicao; j++) {
							char t = LETRAS_MINUSCULAS[random.nextInt(LETRAS_MINUSCULAS.length)];
							senha = senha.concat(String.valueOf(t));
						}

						break;
					case 3:

						if (i == (tipo.length - 1)) {
							qtdePorComposicao = tamanho - senha.length();
						}

						for (int j = 0; j < qtdePorComposicao; j++) {
							char t = NUMEROS[random.nextInt(NUMEROS.length)];
							senha = senha.concat(String.valueOf(t));
						}
						break;
					case 4:

						if (i == (tipo.length - 1)) {
							qtdePorComposicao = tamanho - senha.length();
						}

						for (int j = 0; j < qtdePorComposicao; j++) {
							char t = ESPECIAIS[random.nextInt(ESPECIAIS.length)];
							senha = senha.concat(String.valueOf(t));
						}
						break;
					}
				}
			}
		}
		return senha;
	}
}
