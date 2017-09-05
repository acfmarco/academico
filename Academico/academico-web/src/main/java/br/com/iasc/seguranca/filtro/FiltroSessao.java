package br.com.iasc.seguranca.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.com.iasc.seguranca.dto.LoginWebDTO;
import br.com.iasc.seguranca.servico.AmbienteServico;

/**
 * Filtro responsável por validar se a requisição está validada para a acesso as páginas restritas da aplicação.
 *
 * @author Marco Figueiredo
 * @since 26/09/2016
 * 
 * Copyright notice (c) 2016 BBPrevidência S/A 
 */
public class FiltroSessao implements Filter {

	public static Logger log = Logger.getLogger(FiltroSessao.class);

	public static final String TOKEN_SESSAO_VALIDADA = "logado";
	private static final String PAGINA_INICIAL = "login.jsf";

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		HttpSession session = req.getSession();
        LoginWebDTO usuario = (LoginWebDTO)session.getAttribute("login");
        if(usuario == null){
        	session.setAttribute("msg","Você não está logado no sistema!");
        	String retorno = buscarRetorno(req);
        	
              ((HttpServletResponse)resp).sendRedirect(retorno);
              
        }else{
              chain.doFilter(req, resp);
        }

	}

	private String buscarRetorno(HttpServletRequest req) {
		String retorno = null;

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
		AmbienteServico ambienteServico = context.getBean(AmbienteServico.class);

		retorno = ambienteServico.getAcademico() + PAGINA_INICIAL;
		
		return retorno;
	}

	public void destroy() {
	}

}
