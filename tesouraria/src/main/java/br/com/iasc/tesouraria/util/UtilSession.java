package br.com.iasc.tesouraria.util;

import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.context.AppContext;

/**
 * Classe utilitária para gerenciar objetos na sessão.
 * 
 * @author Tiago Menezes
 * @since 24/10/2012
 * 
 * Copyright notice (c) 2012 BBPrevidência S/A 
 */
public abstract class UtilSession {

	/**
	 * 
	 */
	private static final String CONTEXTO_APLICACAO = "/academico-web";
	private static final String SUFIXO = ".xhtml";
	private static final String USUARIO_KEY = "usuario";
	public static final String COOKIE_TIPO_USUARIO = "tipoUsuario"; //Cookie para armazenar tipo de usuário após invalidar sessão

	/**
	 * Método responsável por obter o contexto do JSF.
	 * 
	 * @return FacesContext
	 */
	public static FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	/**
	 * Método responsável por obter o objeto do tipo ExternalContext.
	 * 
	 * @return ExternalContext
	 */
	public static ExternalContext getExternalContext() {
		return getContext().getExternalContext();
	}

	/**
	 * 
	 * @return ELContext
	 */
	public static ELContext getELContext() {
		return getContext().getELContext();
	}

	/**
	 * 
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		ApplicationContext ctx = AppContext.getApplicationContext();
		return ctx;
	}

	/**
	 * 
	 * @return ExpressionFactory
	 */
	public static ExpressionFactory getExpressionFactory() {
		return getApplication().getExpressionFactory();
	}

	/**
	 * 
	 * @param controlador
	 * @param nomeObjeto
	 * @param propriedade
	 * @return ValueExpression
	 */
	public static ValueExpression getValueExpression(String controlador, String nomeObjeto, String propriedade) {
		final String binding = "#{" + controlador + "." + nomeObjeto + "." + propriedade + "}";
		final ValueExpression expression = getExpressionFactory().createValueExpression(getELContext(), binding, String.class);
		return expression;
	}

	/**
	 * Método responsável por adicionar o usuário passado como argumento na
	 * sessão.
	 * 
	 * @param usuario
	 */
	/*public static void adicionarUsuarioSessao(Usuario usuario) {
		adicionarObjetoSessao(USUARIO_KEY, usuario);
	}*/

	/**
	 * Método responsável por remover o usuário passado como argumento na
	 * sessão.
	 * 
	 * @param usuario
	 */
	public static void removerUsuarioSessao() {
		removerObjetoSessao(USUARIO_KEY);
	}

	/**
	 * Método responsável por recuperar o objeto usuário que se encontra na
	 * sessão.
	 * 
	 * @return Usuario
	 */
	public static Usuario getUsuarioSessao() {
		return (Usuario) getExternalContext().getSessionMap().get(USUARIO_KEY);
	}

	/**
	 * Método responsável por recuperar o objeto usuário que se encontra na
	 * sessão.
	 * 
	 * @return Usuario
	 */
	/*public static br.com.bbprevidencia.cadastroweb.dto.Usuario getUsuarioSessaoCompleto() {
		return (br.com.bbprevidencia.cadastroweb.dto.Usuario) getExternalContext().getSessionMap().get(USUARIO_COMUM_KEY);
	}*/

	/**
	 * Método responsável por recuperar um objeto que se encontra na
	 * sessão.
	 * 
	 * @return Usuario
	 */
	public static Object getVariavelSessao(String chave) {
		return getExternalContext().getSessionMap().get(chave);
	}

	/**
	 * Recupera o login do usuário na sessão.
	 * 
	 * @return LoginTemporariaDTO
	 */
	/*public static LoginBBPrevWebDTO getLoginSessao() {
		return (LoginBBPrevWebDTO) UtilSession.getExternalContext().getSessionMap().get("loginTemporario");
	}*/

	/**
	 * Método responsável por adicionar o objeto passado como argumento na
	 * sessão, através da chave passado como argumento.
	 * 
	 * @param key
	 * @param obj
	 */
	public static void adicionarObjetoSessao(String key, Object obj) {
		getExternalContext().getSessionMap().put(key, obj);
	}

	/**
	 * Método responsável por remover o objeto que se encontra na sessão,
	 * através da chave passada como argumento.
	 * 
	 * @param key
	 */
	public static void removerObjetoSessao(String key) {
		if (getExternalContext().getSessionMap().containsKey(key)) {
			getExternalContext().getSessionMap().remove(key);
		}
	}

	/**
	 * Método responsável por disponibilizar o usuário
	 * da sessão.
	 * 
	 * @return {@link Usuario}
	 */

	/*public static Usuario consultarUsuarioSessao() {

		Usuario usuario = (Usuario) getExternalContext().getUserPrincipal();

		return usuario;
	}*/

	/**
	 * Método responsável por invalidar a sessão corrente 
	 * 
	 */

	public static void invalidarSessaoCorrente() {

		getExternalContext().invalidateSession();
	}

	public static void limparSessao() {
		getExternalContext().getSessionMap().clear();
	}

	/**
	 * 
	 * @return Application
	 */
	public static Application getApplication() {
		return getContext().getApplication();
	}

	/**
	 * 
	 * @return ServletContext
	 */
	public static ServletContext getServletContext() {
		return (ServletContext) getContext().getExternalContext().getContext();
	}

	/**
	 * Retorna o caminho do recurso informado como parametro.
	 * 
	 * @param recurso
	 * @return String
	 */
	public static String getRealPath(String recurso) {
		ServletContext servletContext = (ServletContext) getExternalContext().getContext();
		return servletContext.getRealPath(recurso);
	}

	/**
	 * Retorna uma instancia do controlador através do nome do 
	 * controlador passado como argumento.
	 * 
	 * @param nomeControlador
	 * @return Object
	 */
	@SuppressWarnings("deprecation")
	public static Object getController(String nomeControlador) {
		return getApplication().createValueBinding("#{" + nomeControlador + "}").getValue(UtilSession.getContext());
	}

	/**
	 * 
	 * @return OrigemAcesso
	 */
	/*public static OrigemAcesso getOrigemAcesso() {

		OrigemAcesso acesso = null;

		String pagina = ((HttpServletRequest) UtilSession.getExternalContext().getRequest()).getRequestURI();

		if (StringUtils.isNotBlank(pagina)) {

			if (pagina.contains("mantemParticipanteImpressao")) {

				if (getLoginSessao() != null && getLoginSessao().isBBPrevidenciaOuPatrocinadora()) {
					acesso = OrigemAcesso.GESTAO_PARTICIPANTE;
				} else {
					acesso = OrigemAcesso.ACESSO_CANDIDATO;
				}

			} else if (pagina.contains("acessoIdentificado")) {
				acesso = OrigemAcesso.ACESSO_CANDIDATO;
			} else {
				acesso = OrigemAcesso.GESTAO_PARTICIPANTE;
			}
		}

		return acesso;
	}*/

	/**
	 * Retorna o login atual da sessão atual.
	 * 
	 * @param origemAcesso
	 * @return LoginBBPrevWebDTO
	 */
	/*public static LoginBBPrevWebDTO getLoginAtualBBPrevWeb(OrigemAcesso origemAcesso) {

		if (origemAcesso.equals(OrigemAcesso.ACESSO_CANDIDATO)) {
			return (LoginBBPrevWebDTO) UtilSession.getVariavelSessao(Constantes.VARIAVEL_ACESSO_CANDIDATO);
		}
		if (origemAcesso.equals(OrigemAcesso.GESTAO_PARTICIPANTE)) {
			return (LoginBBPrevWebDTO) UtilSession.getVariavelSessao(Constantes.VARIAVEL_GESTAO_PARTICIPANTE);
		}
		if (origemAcesso.equals(OrigemAcesso.ACESSO_PARTICIPANTE)) {
			return (LoginBBPrevWebDTO) UtilSession.getVariavelSessao(Constantes.VARIAVEL_ACESSO_PARTICIPANTE);
		}
		return null;
	}*/

	public static String recuperarValorCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * Adicionar cookie definindo como path o contexto da aplicacao atual.
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void adicionarCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(CONTEXTO_APLICACAO);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * Devolve um cookie para o navegador com o maxAge default de 1 dia.
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void adicionarCookie(HttpServletResponse response, String name, String value) {
		adicionarCookie(response, name, value, 86400);// padrão 1 dia
	}

	public static void removerCookie(HttpServletResponse response, String name) {
		adicionarCookie(response, name, null, 0);

	}

	/**
	 * Retorna o componente de acordo com o identificador passado como argumento.
	 * 
	 * @param identificadorForm
	 * @param identificadorDiv
	 * @param identificadorCampo
	 * @return UIComponent
	 */
	public static UIComponent buscarComponente(String identificadorForm, String identificadorDiv, String identificadorCampo) {

		UIComponent campo = null;

		if (StringUtils.isNotBlank(identificadorForm) && StringUtils.isNotBlank(identificadorDiv) && StringUtils.isNotBlank(identificadorCampo)) {

			UIViewRoot root = getContext().getViewRoot();

			campo = root.findComponent(identificadorCampo);

			if (campo == null) {

				UIComponent formulario = buscarComponente(identificadorForm);

				if (formulario != null) {

					UIComponent grupo = buscarComponente(formulario, identificadorDiv);

					if (grupo != null) {

						campo = buscarComponente(grupo, identificadorCampo);
					}
				}
			}
		}

		return campo;
	}

	/**
	 * 
	 * @param identificador
	 * @return UIComponent
	 */
	public static UIComponent buscarComponente(String identificador) {

		UIViewRoot root = getContext().getViewRoot();

		return buscarComponente(root, identificador);
	}

	/**
	 * 
	 * @param root
	 * @param identificador
	 * @return UIComponent
	 */
	@SuppressWarnings("rawtypes")
	private static UIComponent buscarComponente(UIComponent root, String identificador) {

		if (StringUtils.isNotBlank(identificador)) {
			for (Iterator ite = root.getFacetsAndChildren(); ite.hasNext();) {
				UIComponent ui = (UIComponent) ite.next();
				if (ui.getId().equals(identificador)) {
					return ui;
				}
			}
		}
		return null;
	}

}
