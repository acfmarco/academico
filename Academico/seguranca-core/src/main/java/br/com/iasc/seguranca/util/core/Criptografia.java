package br.com.iasc.seguranca.util.core;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.print.DocFlavor.BYTE_ARRAY;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import br.com.iasc.utils.exception.AcademicoException;

/**
 * Classe responsável por criptografar e descriptografar
 * uma string
 * 
 * @author arquitetura Cobra
 */
@Controller
public class Criptografia {

	private static byte[] armazenador = {};
	private static SecretKey chave;
	private static Cipher criptografia;
	private static Base64 codificador;

	private static Logger log = Logger.getLogger(Criptografia.class);

	/**s
	 * Método inicializador das variáveis 
	 * utilizadas nesta classe.
	 */

	static {

		byte[] chaveGerada = new byte[16]; //esta chave foi gerada desta forma para possibilitar a criptografia e descriptografia 
		//de todas as senhas com a mesma chave.

		chaveGerada[0] = 20;
		chaveGerada[1] = 108;
		chaveGerada[2] = -56;
		chaveGerada[3] = 2;
		chaveGerada[4] = -69;
		chaveGerada[5] = 83;
		chaveGerada[6] = 50;
		chaveGerada[7] = 45;
		chaveGerada[8] = 92;
		chaveGerada[9] = -27;
		chaveGerada[10] = -94;
		chaveGerada[11] = 127;
		chaveGerada[12] = 45;
		chaveGerada[13] = -79;
		chaveGerada[14] = -8;
		chaveGerada[15] = 63;

		try {
			chave = new SecretKeySpec(chaveGerada, "AES");
			System.out.println(chave.getEncoded());

			criptografia = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
			codificador = new Base64(32, armazenador, true);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Método responsável por criptografar uma senha
	 * 
	 * @param plainText
	 * @return {@link String}
	 * @throws Exception
	 */

	public static synchronized String criptografar(String plainText) throws Exception {
		criptografia.init(Cipher.ENCRYPT_MODE, chave);
		byte[] cipherText = criptografia.doFinal(plainText.getBytes());
		return new String(codificador.encode(cipherText));
	}

	/**
	 * Método responsável por descriptografar
	 * uma senha
	 * 
	 * @param codedText
	 * @return {@link String}
	 * @throws Exception
	 */

	public static synchronized String descriptografar(String codedText) throws Exception {
		byte[] cripto = codificador.decode(codedText.getBytes());
		criptografia.init(Cipher.DECRYPT_MODE, chave);
		byte[] descriptogrador = criptografia.doFinal(cripto);
		return new String(descriptogrador);
	}

	/**
	 * Método responsável por gerar a chave
	 * para codificar a senha
	 * 
	 * @return {@link BYTE_ARRAY}
	 * @throws AcademicoException
	 */

	public static byte[] gerarChave() throws AcademicoException {

		byte[] chaveGerada = new byte[50];

		KeyGenerator geraChave;

		try {

			geraChave = KeyGenerator.getInstance("AES");
			geraChave.init(128);
			SecretKey chaveSeguranca = geraChave.generateKey();
			chaveGerada = chaveSeguranca.getEncoded();

			System.out.println(chaveGerada);

		} catch (NoSuchAlgorithmException e) {
			throw new AcademicoException("Não foi possível realizar está operação" + e.getLocalizedMessage());
		}

		return chaveGerada;
	}

}
