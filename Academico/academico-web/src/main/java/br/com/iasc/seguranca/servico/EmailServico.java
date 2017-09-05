package br.com.iasc.seguranca.servico;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import br.com.iasc.seguranca.exception.EmailException;

/**
 * Serviço responsável pelos envios de emails do sistema.
 * 
 * @author Lourival Júnior
 * @since 12/02/2014
 * 
 *        Copyright notice (c) 2014 Banco do Brasil S/A
 */
@Component
public class EmailServico {

	private static final Logger log = Logger.getLogger(EmailServico.class);

	@Autowired(required = false)
	private JavaMailSender mailSender;

	public void enviarEmail(String[] destinatarios, final String titulo, final String texto) throws EmailException {
		MimeMessagePreparator preparator = this.criarDadosEmail(texto, null, null, destinatarios, titulo);
		try {
			log.debug("Enviando email para " + preparator);
			mailSender.send(preparator);
			log.debug("Email enviado sem erros para " + preparator);
		} catch (Exception e) {
			log.error("Erro ao enviar email", e);
			log.error(preparator.toString());
			throw new EmailException(e);
		}
	}

	public void enviarEmail(final String from, String[] to, final String responderPara, final String subject, final String msg) throws EmailException {
		MimeMessagePreparator preparator = this.criarDadosEmail(msg, from, responderPara, to, subject);
		try {
			log.debug("Enviando email para " + preparator);
			mailSender.send(preparator);
			log.debug("Email enviado sem erros para " + preparator);
		} catch (Exception e) {
			log.error("Erro ao enviar email", e);
			log.error(preparator.toString());
			throw new EmailException(e);
		}
	}

	private MimeMessagePreparator criarDadosEmail(final String text, final String from, final String responderPara, final String[] to, final String subject) {
		return new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(to);
				message.setText(text, true);
				message.setSubject(subject);
				message.setSentDate(new Date());

				String remetente = "";
				if (mailSender != null) {
					JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) mailSender;
					remetente = javaMailSenderImpl.getSession().getProperty("mail.from");

					log.debug("From configurado no servidor de aplicação: " + remetente);

					if (StringUtils.isNotBlank(remetente)) {
						message.setFrom(remetente);
					}
				}

				if (StringUtils.isNotBlank(responderPara)) {
					message.setReplyTo(responderPara);
				}

				if (StringUtils.isBlank(remetente) && StringUtils.isNotBlank(from)) {
					message.setFrom(from);
				}
			}
		};
	}

}
