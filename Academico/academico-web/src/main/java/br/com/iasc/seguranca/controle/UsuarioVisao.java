package br.com.iasc.seguranca.controle;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.iasc.seguranca.Entidades.Usuario;
import br.com.iasc.seguranca.bo.UsuarioBO;
import br.com.iasc.seguranca.util.FacesUtils;
import br.com.iasc.seguranca.util.Mensagens;
import br.com.iasc.seguranca.util.UtilSession;
import br.com.iasc.seguranca.util.core.Criptografia;
import br.com.iasc.utils.UtilJava;
import br.com.iasc.utils.seguranca.GeradorSenha;

@Component("usuarioVisao")
@Scope("session")
public class UsuarioVisao implements Serializable {

	private static final long serialVersionUID = 3808047457539720759L;

	private static final String FW_APRESENTACAO = "/template/apresentacao.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;

	private static final String FW_ALTERAR_SENHA = "/paginas/alteraSenha.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;

	private static final String FW_ALTERAR_SENHA_ESQUICIDA = "/alteraSenhaEsquecida.xhtml" + FacesUtils.PARAMETRO_JSF_REDIRECT;

	@Autowired
	private UsuarioBO usuarioBO;

	private boolean statusDesabilitado;

	private boolean apresenta = false;

	private boolean gerouNovaSenha;

	public boolean isApresenta() {
		return apresenta;
	}

	public void setApresenta(boolean apresenta) {
		this.apresenta = apresenta;
	}

	private Usuario usuarioSessao;

	private Long codigoTipoPessoa;

	private boolean alteracao = false;

	private boolean novoUsuario = false;

	private boolean mostraCPF = false;

	private boolean mostrarSenha = false;

	private Usuario usuario;

	/**
	 * Construtor padrão
	 */
	public UsuarioVisao() {
		super();
		this.usuarioSessao = obterNomeUsuarioSessao();
	}
	
	/**
	 * Adiciona a mensage informativa no faces com a mensagem de texto passado como argumento.
	 * 
	 * @param mensagem
	 */
	private void addMsgInfo(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

	/**
	 * Adiciona a mensage de erro no faces.
	 * 
	 * @param mensagem
	 */
	private void addMsgErro(String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}
	
	/**
	 * Método responsável por fazer o redirect para a página de alterar senha
	 * 
	 * @author Rogerio
	 * @return {@link String}
	 */
	public String iniciarAlterarSenha() {
		Usuario usuario = UtilSession.getUsuarioSessao();
		
		return FW_ALTERAR_SENHA;
	}

	/**
	 * Método responsável por gerar a senha automaticamente
	 * 
	 * @return {@link String}
	 */

	// public String gerarSenha(){
	public void gerarSenha() {

		String composicao = "5";

		String senhaGerada = GeradorSenha.gerarSenha(8, composicao);

		try {
			String senhaCript = Criptografia.criptografar(senhaGerada);
			usuario.setUsuaSenha(senhaCript);
			
			novoUsuario = false;

			if (getUsuario().getCodigo() != UtilSession.getUsuarioSessao().getCodigo()) {
				getUsuario().setUsuaIndSts(1);
				statusDesabilitado = true;
			}
			this.gerouNovaSenha = true;

			Mensagens.addMessage("MSG04");
		} catch (Exception e) {
			Mensagens.addError("ME38");
		}

		// return FW_MANTER_USUARIO;
	}

	/**
	 * Método responsável por alterar a habilitação ou não do campo senha e confirmação senha da página mantemUsuario.xhtml
	 * 
	 * @param event
	 * @return {@link String}
	 */

	public void alterarStatus(AjaxBehaviorEvent evento) {

		UIInput input = (UIInput) evento.getComponent();

		Object valorSubmetido = input.getSubmittedValue();

		if (valorSubmetido.toString().equalsIgnoreCase("N")) {
			novoUsuario = false;
		} else {
			novoUsuario = true;
		}
	}


	/**
	 * Método responsável por fazer logoff do usuário logado.
	 * 
	 * @param eventoSistema
	 */
	public String deslogarUsuario() {
		UtilSession.invalidarSessaoCorrente();
		return FW_APRESENTACAO;
	}

	/**
	 * Método responsável por gerar uma nova senha para usuários que esqueceram a senha
	 * 
	 * @return {@link String}
	 */

	public String gerarNovaSenha() {
		try {

			Usuario usuarioValidado = usuarioBO.atualizarUsuarioPorLogin(usuario);

			StringBuilder texto = new StringBuilder();
			texto.append("Bem vindo aos sistemas da BB Previdência. ");
			texto.append("Sua nova senha é: ");
			texto.append("</b> " + usuarioValidado.getUsuaSenha() + "</b>");

			String[] destinatarios = null;
			if (UtilJava.isStringVazia(usuarioSessao.getUsuaEmail())) {
				destinatarios = new String[] { "demandas.getec@bbprevidencia.com.br" };
			} else {
				destinatarios = new String[] { usuarioSessao.getUsuaEmail() };
			}

			//this.emailServico.enviarEmail(destinatarios, SenhaVisao.ASSUNTO_ALTERAR_SENHA, texto.toString());
			usuario = new Usuario();

			addMsgInfo("Alteração de senha enviada para o e-mail cadastrado!");

		} catch (Exception e) {
			addMsgErro("Informações incorretas. Não foi possível gerar a alteração de senha.");
		}

		return FW_ALTERAR_SENHA_ESQUICIDA;
	}

	/**
	 * Método responsável por obter e fornecer os dados do usuário que está logado.
	 * 
	 * @return {@link String}
	 */

	public Usuario obterNomeUsuarioSessao() {

		return UtilSession.getUsuarioSessao();
	}

	/**
	 * Método responsável por limpar os parâmetros contídos na sessão
	 */
	public void limparSessao() {
		UtilSession.limparSessao();
	}

	public Date getDataNascimentoMinima() {
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, 1);
		return c.getTime();
	}

	public int getFaixaAno() {
		Calendar cal = Calendar.getInstance();
		int ano = cal.get(Calendar.YEAR);
		return (1900 - ano) * (-1);
	}

	public Date getDataNascimentoMaxima() {
		return new Date();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public boolean isNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(boolean novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

	public boolean isMostrarSenha() {
		return mostrarSenha;
	}

	public void setMostrarSenha(boolean mostrarSenha) {
		this.mostrarSenha = mostrarSenha;
	}

	public boolean isMostraCPF() {
		return mostraCPF;
	}

	public void setMostraCPF(boolean mostraCPF) {
		this.mostraCPF = mostraCPF;
	}

	public Long getCodigoTipoPessoa() {
		return codigoTipoPessoa;
	}

	public void setCodigoTipoPessoa(Long codigoTipoPessoa) {
		this.codigoTipoPessoa = codigoTipoPessoa;
	}

	public Usuario getUsuarioSessao() {
		
		if (usuarioSessao == null){
			return UtilSession.getUsuarioSessao();
		}
		
		return usuarioSessao;
	}

	public void setUsuarioSessao(Usuario usuarioSessao) {
		this.usuarioSessao = usuarioSessao;
	}

	public boolean isStatusDesabilitado() {
		return statusDesabilitado;
	}

	public void setStatusDesabilitado(boolean statusDesabilitado) {
		this.statusDesabilitado = statusDesabilitado;
	}

	public boolean isGerouNovaSenha() {
		return gerouNovaSenha;
	}

	public void setGerouNovaSenha(boolean gerouNovaSenha) {
		this.gerouNovaSenha = gerouNovaSenha;
	}

	
}
