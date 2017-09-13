package br.com.iasc.seguranca.controle;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.iasc.seguranca.Entidades.InformacaoSistema;
import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.bo.UsuarioBO;
import br.com.iasc.seguranca.dto.LoginWebDTO;
import br.com.iasc.seguranca.util.FacesUtils;
import br.com.iasc.seguranca.util.Mensagens;
import br.com.iasc.seguranca.util.UtilSession;
import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.arquivo.PropriedadeUtil;
import br.com.iasc.utils.exception.AcademicoException;
import br.com.iasc.utils.exception.InfraException;

/**
 * Classe responsável por autenticar e validar o usuário logado no sistema
 * 
 * @author Marco Figueiredo
 */
@Scope("session")
@Component("autenticacaoVisao")
public class AutenticacaoVisao implements Serializable {

	private static final long serialVersionUID = -1L;
	private static final Logger logger = Logger.getLogger(AutenticacaoVisao.class);

	private static final String PAGINA_INICIAL = "template/apresentacao.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;
	private static final String PAGINA_LOGIN = "erroLogin.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;
	private static final String PAGINA_REDEFINIR_SENHA = "alteraSenhaEsquecida.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;

	private String usuario;
	private String senha;

	private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioBO usuarioBO;

	public AutenticacaoVisao() {
		super();
	}

	public String logar() {

		try {
			Usuario usuarioLogado = this.usuarioBO.autenticarUsuario(usuario, senha);

			if (usuarioLogado != null) {

				LoginWebDTO login = new LoginWebDTO();
				login.setUsuarioSessao(usuarioLogado);

				Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioLogado, getPerfil(), createAuthorities(usuarioLogado));
				Authentication result = authenticationManager.authenticate(authentication);

				SecurityContextHolder.getContext().setAuthentication(result);

				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
				session.setAttribute("login", login);

				return PAGINA_INICIAL;

			}

		} catch (Exception e) {
			logger.error(e);
			Mensagens.addMsgErro(e.getMessage());
			return PAGINA_LOGIN;
		}

		return PAGINA_LOGIN;
	}

	public String redefinirSenha() {
		return PAGINA_REDEFINIR_SENHA;
	}

	public void limparForm() {
		usuario = "";
		senha = "";
	}

	private Collection<GrantedAuthority> createAuthorities(Usuario usuario) {
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
		result.add(new GrantedAuthorityImpl("Admin"));
		return result;
	}

	private String getPerfil() {
		return "Admin";
	}

	/**
	 * Método responsável por fornecer dados dos sistema com o qual o sistema está logando.
	 * 
	 * @return {@link InformacaoSistema}
	 */
	private InformacaoSistema obterInformacaoSistema() {

		try {

			Properties propriedades = PropriedadeUtil.carregarPropriedades("amb/sistema.properties");
			String codigo = propriedades.getProperty("sistema.codigo");
			String nome = propriedades.getProperty("sistema.nome");

			InformacaoSistema informacaoSistema = new InformacaoSistema();
			informacaoSistema.setCodigo(codigo);
			informacaoSistema.setNome(nome);

			return informacaoSistema;

		} catch (InfraException e) {
			logger.error(e);
			throw new AcademicoException(e.getMessage());
		}
	}

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public String getUsuario() {

		if (UtilSession.getUsuarioSessao() != null && (this.usuario == null || this.usuario.isEmpty())) {
			return UtilSession.getUsuarioSessao().getUsuaNome();
		}
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getVersaoSistema() {
		try {
			InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(inputStream);
			Attributes attributes = manifest.getMainAttributes();
			return attributes.getValue("Implementation-Version");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
