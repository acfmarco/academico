package br.com.iasc.utils.arquivo;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sourceforge.rtf.RTFTemplate;
import net.sourceforge.rtf.helper.RTFTemplateBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import br.com.iasc.utils.exception.InfraException;

/**
 * Classe utilitária que contém métodos para o uso de templates Velocity nos projetos.
 *
 * @author Lourival Júnior
 * @since 04/04/2013
 * 
 * Copyright notice (c) 2013 BBPrevidência S/A
 */
public class UtilTemplates {

	private static final Logger log = Logger.getLogger(UtilTemplates.class);

	public static String executarTemplate(String template, Map<String, Object> contexto) throws InfraException {

		try {
			// Load the velocity properties from the class path
			Properties properties = new Properties();
			properties.load(UtilTemplates.class.getClassLoader().getResourceAsStream("velocity.properties"));

			// Create and initialize the template engine
			VelocityEngine velocityEngine = new VelocityEngine(properties);

			VelocityContext velocityContext = new VelocityContext();

			Set<String> items = contexto.keySet();

			for (String string : items) {
				velocityContext.put(string, contexto.get(string));
			}

			// Execute the template
			StringWriter writer = new StringWriter();
			velocityEngine.evaluate(velocityContext, writer, "utf-8", new StringReader(template));

			return writer.toString();
		} catch (Exception e) {
			log.error(e);
			throw new InfraException("Erro ao processar template velocity", e);
		}
	}

	public static void gerarTemplate(String rtfSource, String rtfTarget, HashMap<String, Object> contexto) {
		// 1. Get default RTFtemplateBuilder
		RTFTemplateBuilder builder = RTFTemplateBuilder.newRTFTemplateBuilder();

		try {
			// 2. Get RTFtemplate with default Implementation of template engine (Velocity) 
			RTFTemplate rtfTemplate = builder.newRTFTemplate();

			// 3. Set the RTF model source 
			rtfTemplate.setTemplate(new File(rtfSource));

			Set<String> keySet = contexto.keySet();

			// 4. Put the context
			for (String chave : keySet) {
				rtfTemplate.put(chave, contexto.get(chave));
			}

			// 5. Merge the RTF source model and the context  
			rtfTemplate.merge(rtfTarget);
		} catch (Exception e) {
			log.error("Erro ao gerar o template RTF:", e);
		}
	}

	public static void main(String[] args) throws ConnectException {
		HashMap<String, Object> contexto = new HashMap<String, Object>();
		contexto.put("nomeParticipante", "Lourival da Conceicao Pereira Junior".toUpperCase());
		contexto.put("cpf", "825.620.832-53");
		contexto.put("nomeDiretor", "Waldemar Costa Neto".toUpperCase());

		File file = FileUtils.toFile(UtilTemplates.class.getClassLoader().getResource("template.rtf"));

		gerarTemplate(file.getAbsolutePath(), "resultado.rtf", contexto);
	}

}
