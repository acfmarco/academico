package br.com.iasc.utils.arquivo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.iasc.utils.exception.InfraException;

/**
 * Classe utilitária para manipulação de arquivos properties.
 *
 * @author Tiago Menezes
 * @since 05/11/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public final class PropriedadeUtil implements Serializable {

	private static final long serialVersionUID = 6152474902657198094L;
	private static Logger log = Logger.getLogger(PropriedadeUtil.class);

	private static Map<String, PropriedadeUtil> mapaProperties = new HashMap<String, PropriedadeUtil>();

	private Properties properties;

	/**
	 * Retorna uma instancia de classe PropriedadeUtil
	 * 
	 * @return PropriedadeUtil
	 * @throws InfraException 
	 */
	public static PropriedadeUtil getInstancia(String arquivo) throws InfraException {

		PropriedadeUtil propriedadeUtil = mapaProperties.get(arquivo);

		if (propriedadeUtil == null) {
			propriedadeUtil = new PropriedadeUtil(carregarPropriedades(arquivo));
			mapaProperties.put(arquivo, propriedadeUtil);
		}

		return propriedadeUtil;
	}

	/**
	 * Retorna uma instancia de classe PropriedadeUtil
	 * 
	 * @return PropriedadeUtil
	 * @throws InfraException 
	 */
	public static PropriedadeUtil getInstancia(String arquivo, ClassLoader classLoader) throws InfraException {

		PropriedadeUtil propriedadeUtil = mapaProperties.get(arquivo);

		if (propriedadeUtil == null) {
			propriedadeUtil = new PropriedadeUtil(carregarPropriedades(arquivo, classLoader));
			mapaProperties.put(arquivo, propriedadeUtil);
		}

		return propriedadeUtil;
	}

	/**
	 * Método que retorna um objeto Properties.
	 * 
	 * @param arquivo
	 *            O Caminho físico do arquivo e seu nome.
	 * @return Um objeto Properties.
	 * @throws InfraException 
	 */
	public static Properties carregarPropriedades(String origem) throws InfraException {
		try {

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Properties propriedades = new Properties();
			propriedades.load(classLoader.getResourceAsStream(origem));

			return propriedades;

		} catch (FileNotFoundException e) {
			log.error(e);
			throw new InfraException("Arquivo não encontrado.", e);
		} catch (IOException e) {
			log.error(e);
			throw new InfraException("Erro na leitura do arquivo.", e);
		} catch (Exception e) {
			log.error(e);
			throw new InfraException("Arquivo não encontrado.", e);
		}
	}

	/**
	 * Método que retorna um objeto Properties.
	 * 
	 * @param arquivo
	 *            O Caminho físico do arquivo e seu nome.
	 * @return Um objeto Properties.
	 * @throws InfraException 
	 */
	public static Properties carregarPropriedades(String origem, ClassLoader classLoader) throws InfraException {
		try {

			Properties propriedades = new Properties();
			propriedades.load(classLoader.getResourceAsStream(origem));

			return propriedades;

		} catch (FileNotFoundException e) {
			log.error(e);
			throw new InfraException("Arquivo não encontrado.", e);
		} catch (IOException e) {
			log.error(e);
			throw new InfraException("Erro na leitura do arquivo.", e);
		} catch (Exception e) {
			log.error(e);
			throw new InfraException("Arquivo não encontrado.", e);
		}
	}

	/**
	 * Construtor
	 */
	protected PropriedadeUtil(Properties properties) {
		super();
		this.properties = properties;
	}

	/**
	 * Método que retorna o valor de uma propriedade de acordo com o seu nome.
	 * 
	 * @param propriedades
	 *            Um objeto do tipo Properties.
	 * @param propriedade
	 *            Nome da propriedade.
	 * @return O valor da propriedade.
	 */
	public String getPropriedade(String propriedade) {
		return properties.getProperty(propriedade);
	}

	/**
	 * Método que retorna o valor de uma propriedade de acordo com o seu nome.
	 * 
	 * @param propriedades
	 *            Um objeto do tipo Properties.
	 * @param propriedade
	 *            Nome da propriedade.
	 * @param argumentos
	 *            valores do parametros.
	 * @return O valor da propriedade.
	 * @throws InfraException 
	 */
	public String getPropriedade(String propriedade, String... argumentos) throws InfraException {

		String msgParam = null;

		String msg = properties.getProperty(propriedade);

		for (Integer x = 0; x < argumentos.length; x++) {

			msgParam = msg.replace("{0}".replace("0", x.toString()), argumentos[x]);

			if (msgParam == msg) {
				throw new InfraException("{0}".replace("0", x.toString()));
			} else {
				msg = msgParam;
			}
		}

		return msg;
	}

}
