package br.com.iasc.seguranca.util;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.iasc.utils.exception.AcademicoException;

public class Mensagens {

	private static Logger logger = Logger.getLogger(Mensagens.class);
	private static ResourceBundle bundle;

	static {
		try {
			bundle = ResourceBundle.getBundle("messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
		} catch (Exception e) {
			logger.error("Erro ao carregar recurso de mensagens", e);
		}
	}

	public Mensagens() {
		super();
	}

	public static void addMessage(String msg) {

		try {
			msg = bundle.getString(msg);
		} finally {
			addFacesMessage(msg, FacesMessage.SEVERITY_INFO);
		}

	}

	public static void addError(String msg) {

		try {

			msg = bundle.getString(msg);
		} finally {

			addFacesMessage(msg, FacesMessage.SEVERITY_ERROR);
		}

	}

	/**
	 * Levanta a mensagem de erro de acordo mensagem do pacote .properties e os parâmetros passados.
	 * 
	 * @param msg código de mensagem em messages.properties
	 * @param argumentos valores a serem substituídos nos parâmetros ({0}, {1}, {2}, {3}, etc)
	 * @throws ParametroMensagemInexistenteException Levanta a exceção caso o parâmetro recorrido não exista. Por exemplo: passou 3 parâmetro, mas a mensagem contém apenas 3. O 3o parâmetro irá acusar
	 *             o erro.
	 */
	public static void addError(String msg, String... argumentos) {

		String msgParam = getMensagem(msg, argumentos);

		addFacesMessage(msgParam, FacesMessage.SEVERITY_ERROR);
	}

	/**
	 * 
	 * @param msg
	 * @param argumentos
	 * @return String
	 */
	public static String getMensagem(String msg, String... argumentos) {

		String msgParam = null;
		msg = bundle.getString(msg);

		for (Integer x = 0; x < argumentos.length; x++) {

			msgParam = msg.replace("{0}".replace("0", x.toString()), argumentos[x]);

			if (msgParam == msg) {
				throw new AcademicoException("{0}".replace("0", x.toString()));
			} else {
				msg = msgParam;
			}
		}
		return msgParam;
	}

	/**
	 * Levanta a mensagem de informaçao de acordo mensagem do pacote .properties e os parâmetros passados.
	 * 
	 * @param msg código de mensagem em messages.properties
	 * @param argumentos valores a serem substituídos nos parâmetros ({0}, {1}, {2}, {3}, etc)
	 * @throws ParametroMensagemInexistenteException Levanta a exceção caso o parâmetro recorrido não exista. Por exemplo: passou 3 parâmetro, mas a mensagem contém apenas 3. O 3o parâmetro irá acusar
	 *             o erro.
	 */
	public static void addMessage(String msg, String... argumentos) throws AcademicoException {

		String msgParam = getMensagem(msg, argumentos);

		addFacesMessage(msgParam, FacesMessage.SEVERITY_INFO);

	}

	public static void addWarn(String msg) {

		try {

			msg = bundle.getString(msg);
		} finally {

			addFacesMessage(msg, FacesMessage.SEVERITY_WARN);
		}
	}

	private static void addFacesMessage(String msg, Severity severity) {
		FacesMessage fm = new FacesMessage(severity, "", msg);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}

	/**
	 * Adiciona a mensage informativa no faces com a mensagem de texto passado como argumento.
	 * 
	 * @param mensagem
	 */
	public static void addMsgInfo(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	/**
	 * Adiciona a mensage informativa no faces com a mensagem de texto passado como argumento.
	 * 
	 * @param mensagem
	 */
	public static void addMsgWarn(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "", mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	/**
	 * Adiciona a mensage de erro no faces.
	 * 
	 * @param mensagem
	 */
	public static void addMsgErro(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	public static void addMsgErro(List<String> listaMesagens) {
		for (String mensagem : listaMesagens) {
			addMsgErro(mensagem);
		}
	}

	/**
	 * Método utilizado para criar um objeto do tipo FacesMessage, mas sem adicionar no FacesContext. Obs.: Método utill para classes de validadores. Exemplo: CompetenciaInicioFimValidator by:
	 * helio.lima
	 * 
	 * @return
	 */
	public static FacesMessage getFacesMessageErro(String codigoMensagem, String... parametrosMensagem) {
		FacesMessage facesMessage = null;
		try {
			String msg = bundle.getString(codigoMensagem);
			int index = 0;
			for (String stringPatrametro : parametrosMensagem)
				msg = msg.replace("{" + (index++) + "}", stringPatrametro);

			facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesMessage.setSummary(msg);

		} catch (Exception e) {
			addMsgErro("Passagem de parametros errado em mensagens: Código da Mensagem" + codigoMensagem);
		}

		return facesMessage;
	}

}
